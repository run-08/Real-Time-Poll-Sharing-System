import { useEffect, useState } from "react";
import { Outlet, useNavigate } from "react-router-dom";
import { Bar } from "../NavBar/Bar";
import { PollQuestion } from "./PollQuestion";
export const HomePage = () => {
    const [isPoll,setIsPoll] = useState(false);
    const navigate = useNavigate();
    useEffect(()=>{
       const token = localStorage.getItem("token");
        if(token === undefined || token === null){
        navigate("/signup");
        return;
    }
    },[]);
    return (
        <>
        <Bar></Bar>
         <div className="container  min-h-screen flex flex-col justify-center items-center m-0 p-0 min-w-screen bg-gradient-to-br from-[#7c42da] via-[#d11bb4] to-[#e5e500]">
            <div className={`sub-container ${isPoll ? `hidden`:"block"} text-center`}>
                <p className="text-3xl text-gray-100">Do you wanna make a poll?</p>
                <div className="question_box">
                    <div className="yes-box my-1 mt-10 ">
                       <span 
                       className="cursor-pointer border-2 rounded-2xl py-2 px-5 text-white font-bold text-xl  mx-1"
                       onClick={() => setIsPoll(true)}
                       >Yes</span>
                       <span className="cursor-pointer border-2 rounded-2xl py-2 px-5 text-white font-bold text-xl  mx-1">No</span>
                    </div>
                </div>
            </div>
            <Outlet></Outlet>
            {isPoll && <PollQuestion></PollQuestion>}
        </div>
        </>
    )
}