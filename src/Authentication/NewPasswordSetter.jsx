import { useEffect, useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import OTPBoxGenerator from "../Component/OTPBoxGenerator";
import { validatePassword } from "./ValidatePassword";
const NewPasswordSetter = () => {
    const[email,setEmail] = useState(null);
    const[OTPNo,setOTPNo] = useState(null);
    const[enableDigitOTP,setEnableDigitOTP] = useState(false);
    const[resetOTP,setResetOTP] = useState(true);
    const[timerSeconds,setTimerSeconds] = useState("00");
    const [userOTP,setUserOTP] = useState([0,0,0,0]);
    const[verifiedOTP,setVerifiedOTP] = useState(false);
    const[password,setNewPassword] = useState(null);
    const changeUserPassword = async() => {
      if(validatePassword(password)){
         const body = {
            email:email,
            password:password,
         };
         console.log(body);
         
         const response = await fetch("http://localhost:1000/api/changePassword",{
            method:"PUT",
            headers:{
               "Content-Type":"application/json",
            },
            body:JSON.stringify(body),
         });
         if(response.ok){
            alert("Password changed successfully!")
            navigate("/signup");
         }
         else{
            const map_error = await response.json();
            alert(map_error?.Error);
         }
      }
      else{
         alert("Password needs to be strong!")
      }
    }
     const inputs = useRef([]);
     const verifyOTP = () => {
      console.log(OTPNo);
         let temp_user_OTP = 0;
         userOTP.map((item) =>{
            temp_user_OTP *= 10;
            temp_user_OTP += parseInt(item)
         });
         console.log(temp_user_OTP);

         if(OTPNo != temp_user_OTP){
            alert("Invalid OTP!");
         }else{
            setVerifiedOTP(true);
         }
     }
   
     const handleOTPTime = () => {
      if(!resetOTP){
         return;
      }
      setResetOTP(false);
      setTimeout(()=>{   
          setResetOTP(true);
      },1000*60);
      let seconds = 59 ;
      const intervalId = setInterval(()=>{
         if(seconds < 10){
         setTimerSeconds(`0${seconds}`)
         }
         else{
            setTimerSeconds(seconds)
         }
         seconds -= 1;
         if(seconds < 0){
            clearInterval(intervalId);
         }
      },1000)
     }
     useEffect(()=>{
      console.log(OTPNo);
        setTimeout(()=>setOTPNo(null),5000 * 60);
     },[OTPNo])
  const handleKey = (e, index) => {   
    if (e.key === "ArrowLeft" && index > 0) {
      inputs.current[index - 1].focus();
    }

    if (e.key === "ArrowRight" && index < inputs.current.length - 1) {
      inputs.current[index + 1].focus();
    }
  };
     const navigate = useNavigate();
     const randomeGenerator = () => {
        const MIN = 1000;
        const MAX = 9999;
        const PSEUDO_RANDOM = Math.floor(Math.random() * (MAX - MIN + 1)) + MIN;
        return PSEUDO_RANDOM;
     }
     const sendOTP = async() => {
        const OTP = randomeGenerator();
        setOTPNo(OTP);
        const emailRequest={
           "emailId":email,
           "OTP":OTP,
        }
        const response = await fetch("http://localhost:1000/api/sendOTP",{
           method:"PUT",
           headers:{
             "Content-Type":"application/json",
           },
           body:JSON.stringify(emailRequest),
        })
        if(!response.ok){
            const map_error = await response.json();
            console.log(map_error);
            alert(map_error?.Error);
        }
        else{
           setEnableDigitOTP(true);
           handleOTPTime();
        }
     }
   return (
    <div className="bg-gradient-to-br  from-[#7c42da] via-[#d11bb4] to-[#e5e500]  min-h-screen">
         {!verifiedOTP && <div className="user-email flex justify-center ">
            <div className="user-email-box  w-100 md:w-100 p-3 min-h-25 my-12 rounded-2xl md:p-5 bg-white    ">
               <label htmlFor="email" className="cursor-pointer font-bold">Enter Your Email ID : </label>
               <input 
               onChange={(e) => setEmail(e.target.value)}
               className="tetx-bold border w-60 md:w-auto border-transparent border-b-black focus:outline-none  text-blue-600 font-bold text-xl"  id="email"></input>
                <div className="User-OTP-No ">
                  {enableDigitOTP && 
                     <div className="grid grid-cols-4 gap-10">
                      <OTPBoxGenerator idx={0} inputs={inputs} handleKey={handleKey} userOTP={userOTP} setUserOTP={setUserOTP}/>
                      <OTPBoxGenerator idx={1} inputs={inputs} handleKey={handleKey} userOTP={userOTP} setUserOTP={setUserOTP}/>
                      <OTPBoxGenerator idx={2} inputs={inputs} handleKey={handleKey} userOTP={userOTP} setUserOTP={setUserOTP}/>
                      <OTPBoxGenerator idx={3} inputs={inputs} handleKey={handleKey} userOTP={userOTP} setUserOTP={setUserOTP}/>
                     </div> 
                  } 
                 {!resetOTP && <span className="text-black fe font-bold">Resend OTP in <span className=" text-red-500 font-bold">00:{timerSeconds}</span></span>}
               <div className={`generate-OTP   flex gap-10`}>
                <span 
                onClick={() =>{
                   sendOTP();
                }}
                className={`OTP-Box font-bold text-white outline-2 bg-black my-3 py-2 rounded-xl w-20 text-center ${resetOTP ? "cursor-pointer":"cursor-wait"} ${resetOTP ? "":"opacity-10"}`}>
                    Get OTP
                </span>
                <span 
                onClick={()=>{
                  verifyOTP();
                  setUserOTP([]);
                }}
                className="font-bold text-white outline-2  bg-black my-3 py-2 rounded-xl w-20 text-center cursor-pointer"> Verify</span>
                </div>
                </div>
            </div>
         </div>}
         {verifiedOTP && <div className="new-password-box relatives">
            <div className="grid gird-cols-2 justify-center gap-y-2 gap-x-3">
               <label htmlFor="new-password" className="cursor-pointer font-bold px-2 ">
               Enter your new Password: 
            </label>
            <input
            onChange={(e) => setNewPassword(e.target.value)}
            className=" border-transparent font-bold w-100 h-10 rounded-2xl px-3 bg-white text-blue-600 focus:outline-none  border-b-blue-600"
             type="password" placeholder="Enter your new password here!" id="new-password"></input>  
             <div className="confirm text-center font-bold text-white my-2">
                <span
                onClick={() => changeUserPassword()}
                 className="border bg-black rounded-2xl px-2 hover:bg-white hover:text-black py-2 cursor-pointer">Confirm</span>
            </div>
         
         </div>
         </div>}
    </div>
   )
}
export default NewPasswordSetter;