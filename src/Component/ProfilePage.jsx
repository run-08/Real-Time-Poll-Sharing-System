import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

export const ProfilePage = () => {
    const name = localStorage.getItem("name");
    const email = localStorage.getItem("email");  
    const[userCount, setUserCount] = useState(0)  
    const navigate = useNavigate()
    useEffect(()=>{
       const token = localStorage.getItem("token");
        if(token === undefined || token === null){
        navigate("/signup");
        return;
    }
    },[]);
    return (
        <div className="container  min-h-screen flex flex-col justify-center items-center m-0 p-0 min-w-screen bg-gradient-to-br from-[#7c42da] via-[#d11bb4] to-[#e5e500]">
            <div className="profile-container py-20 w-60 md:w-120">
                <div className="bg-white outline-2  px-2 md:text-2xl outline-white rounded-xl  py-2 ">
                    <div className="userPhtoto-section flex justify-center ">
                        <div className="photo outline-purple-400">
                            <img src="#" className="rounded-full cursor-pointer outline-purple-400"></img>
                        </div>
                    </div>
                    <div className="name-box py-4 border-2 border-transparent border-b-black">
                        <div className="name-field">
                            <span className="font-bold px-2 ">Name: </span>
                            <input 
                            className="name"    
                            value={name}
                            disabled
                            />
                            <svg className="w-10 h-7 px-2 cursor-pointer m-0 text-purple-400" fill="purple" xmlns="http://www.w3.org/2000/svg" viewBox="0 -10 550 512"><path d="M402.6 83.2l90.2 90.2c3.8 3.8 3.8 10 0 13.8L274.4 405.6l-92.8 10.3c-12.4 1.4-22.9-9.1-21.5-21.5l10.3-92.8L388.8 83.2c3.8-3.8 10-3.8 13.8 0zm162-22.9l-48.8-48.8c-15.2-15.2-39.9-15.2-55.2 0l-35.4 35.4c-3.8 3.8-3.8 10 0 13.8l90.2 90.2c3.8 3.8 10 3.8 13.8 0l35.4-35.4c15.2-15.3 15.2-40 0-55.2zM384 346.2V448H64V128h229.8c3.2 0 6.2-1.3 8.5-3.5l40-40c7.6-7.6 2.2-20.5-8.5-20.5H48C21.5 64 0 85.5 0 112v352c0 26.5 21.5 48 48 48h352c26.5 0 48-21.5 48-48V306.2c0-10.7-12.9-16-20.5-8.5l-40 40c-2.2 2.3-3.5 5.3-3.5 8.5z"/></svg>
                        </div>
                        
                    </div>
                    <div className="email-box py-4  border-2 border-transparent border-b-black">
                        <div className="col-span-2">
                            <span  className="font-bold px-2">Email: </span>
                            <input className="email overflow-hidden "
                             value={email}
                             disabled
                            />
                        </div>  
                        <svg className="w-10 h-7 px-2 cursor-pointer m-0 text-purple-400" fill="purple" xmlns="http://www.w3.org/2000/svg" viewBox="0 -10 550 512"><path d="M402.6 83.2l90.2 90.2c3.8 3.8 3.8 10 0 13.8L274.4 405.6l-92.8 10.3c-12.4 1.4-22.9-9.1-21.5-21.5l10.3-92.8L388.8 83.2c3.8-3.8 10-3.8 13.8 0zm162-22.9l-48.8-48.8c-15.2-15.2-39.9-15.2-55.2 0l-35.4 35.4c-3.8 3.8-3.8 10 0 13.8l90.2 90.2c3.8 3.8 10 3.8 13.8 0l35.4-35.4c15.2-15.3 15.2-40 0-55.2zM384 346.2V448H64V128h229.8c3.2 0 6.2-1.3 8.5-3.5l40-40c7.6-7.6 2.2-20.5-8.5-20.5H48C21.5 64 0 85.5 0 112v352c0 26.5 21.5 48 48 48h352c26.5 0 48-21.5 48-48V306.2c0-10.7-12.9-16-20.5-8.5l-40 40c-2.2 2.3-3.5 5.3-3.5 8.5z"/></svg>
                    </div>
                    <div className="user-poll-created-count  border-2 py-4 border-transparent">
                        <div className="user-poll-count px-2 my-2"><span className="font-bold ">Poll Created:</span> 
                           <span > {userCount}  </span>
                        </div>
                        <span 
                        className="manage-poll-system font-bold  text-white px-2 py-1 p-1 mx-2 rounded-xl  cursor-pointer bg-gradient-to-br from-[#2196f3] via-[#955cf4] to-[#339999]"
                        onClick={(e) => {
                            navigate("/userPolls")
                        }}
                        >
                            manage
                        </span>
                    </div>
                </div>
            </div>
        </div>
    );
}