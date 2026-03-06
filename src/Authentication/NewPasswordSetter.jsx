import { useState } from "react";
import { useNavigate } from "react-router-dom";
const NewPasswordSetter = () => {
    const[email,setEmail] = useState(null);
    const[OTPNo,setOTPNo] = useState(null);
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
            alert("No, Invalid password!")
        }
        else{
            navigate("/signup")
        }
     }
   return (
    <div className="bg-gradient-to-br from-[#7c42da] via-[#d11bb4] to-[#e5e500]  min-h-screen">
         <div className="user-email flex justify-center ">
            <div className="user-email-box w-100 md:w-100 p-3 min-h-25 my-12 rounded-2xl md:p-5 bg-white    ">
               <label htmlFor="email" className="cursor-pointer font-bold">Enter Your Email ID : </label>
               <input 
               onClick={(e) => setEmail(e.target.value)}
               className="tetx-bold border w-60 md:w-auto border-transparent border-b-black focus:outline-none  text-blue-600 font-bold text-xl"  id="email"></input>
               <div className="generate-OTP my-3 py-2 cursor-pointer outline-2 bg-black rounded-xl w-20 text-center">
                <span 
                onClick={() =>{
                   sendOTP();
                }}
                className="OTP-Box font-bold text-white">
                    Get OTP
                </span>
                <div className="User-OTP-No">
                   <div></div>
                </div>
            </div>
            </div>
         </div>
    </div>
   )
}
export default NewPasswordSetter;