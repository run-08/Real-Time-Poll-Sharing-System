import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { GenerateToken } from "./GenerateToken";
import { validateEmail } from "./ValidateEmail";
import { validatePassword } from "./ValidatePassword";
const SignUp = () => {
    const[email,setEmail] = useState(null); 
    const[password,setPassword] = useState(null);
    const[isSignUp,setisSignUp] = useState(true);
    const[isInvalidEmail,setIsInValidEmail] = useState(false);
    const[isInvalidPassword,setIsInValidPassword] = useState(false);
    const navigator = useNavigate();
    const validateForm = async(e) => {
        e.preventDefault();
        if(!validateEmail(email)){
            setIsInValidEmail(true);
        }
        else{
            setIsInValidEmail(false);
        }
        if(!validatePassword(password)){
            setIsInValidPassword(true);
        }
        else{
            setIsInValidPassword(false);
        }
        // Now get token from Authentication Server and get into Resource server....
        const formData = {
            email:email,
            password:password,
        }
        const flag = await GenerateToken({formData,isSignUp});
        console.log(flag);
        if(flag){
            navigator("/");
        }
    }
    // const[eye,setEye] = useState(eyeIcon);
     return(
        <div 
        className="container min-h-screen flex flex-col justify-center items-center m-0 p-0 min-w-screen bg-gradient-to-br from-[#7c42da] via-[#d11bb4] to-[#e5e500]">
            <div className="sub-container w-75 md:w-100  rounded-t-2xl outline-4 rounded-b-xl outline-white overflow-hidden grid">
                  <div className=" bg-white py-3 cursor-pointer font-bold text-center ">
                     <span className="text-black text-2xl ">{isSignUp ? "Register" : "Login to "} Your Account</span>
                  </div>
                <form className="">
                     <div className="email-field px-5 my-2 ">
                            <label 
                             htmlFor="email"
                              className="text-white font-bold text-xl">Email </label>
                          <input 
                           type="text" 
                           name="email" 
                           id="email" 
                           placeholder="Enter your Email"
                           onChange={(e)=>{
                             setEmail(e.target.value);
                           }}   
                           className="border w-60 md:w-80 border-white text-xl bg-white text-black font-bold outline-white px-2 py-2 rounded-xl mx-2 my-2"
                        />
                        {isInvalidEmail && <span className="text-red-400 mx-10 bg-gray-200 rounded-md px-2  ">Invalid Email!</span>}
                    </div>
                    <div className="password-field px-5 my-2 ">
                        <label 
                             htmlFor="Password"
                              className="text-white font-bold text-xl">Password </label>
                        <input 
                           type="password" 
                           name="Password" 
                           id="Password" 
                           placeholder="Enter your Password"    
                           onChange={(e) => {
                            setPassword(e.target.value);
                           }}
                           className="border border-white w-60 md:w-80 text-xl bg-white text-black font-bold outline-white px-2 py-2 rounded-xl mx-2 my-2"
                        />
                        {isInvalidPassword &&   <span className="text-red-400 mx-10 bg-gray-200 rounded-md px-2  ">Invalid Password!</span>}

                        {/* <img src={eye}
                         className="w-6 h-6  rounded-2xl border bg-white relative m-0 p-0 float-end my-5 cursor-pointer"/> */}
                    </div>
                    <div className="submit-button cursor-pointer hover:bg-black hover:text-white text-center h-10 rounded-mid flex items-center justify-center bg-white">
                       <button 
                       type="submit"
                       className=" font-bold text-2xl  cursor-pointer"
                       onClick={(e) => {
                            validateForm(e);
                       }}
                       >
                        {isSignUp ? "Register" : "Login"}
                       </button>
                    </div>
                </form>
            </div>
            <div className="login-link font-bold text-gray-300 my-1">
                    <span className="">
                        {isSignUp ? "Already have an account?" : "Click here to "}
                        <a 
                        href="#"
                        className={`${isSignUp ? "underline-offset-1" : "underline-offset-3"} underline hover:text-blue-900`}
                         onClick={() => setisSignUp(!isSignUp)}
                        >{isSignUp ? "Click here" : "signup"}</a>
                    </span>
                </div>
        </div>
     );
}


export default SignUp;