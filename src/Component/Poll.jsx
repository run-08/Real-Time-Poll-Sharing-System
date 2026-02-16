import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
export const Poll = () => {
    const [isUserSelected,setIsUserSelected] = useState(false);
    const[userSelectedOption,setUserSelectedOption] = useState(null);
    const location = useLocation();
    const pollData = location?.state;
    const {question} = pollData;
    const {options} = pollData;
    const navigate = useNavigate();
    const token = localStorage.getItem("token");
    useEffect(()=>{
           const token = localStorage.getItem("token");
            if(token === undefined || token === null){
            navigate("/signup");
            return;
    }
    },[]);
    if(options.length < 2){
        alert("Options must be > 2!");
        navigate("/");
        return;
    };
  return (
     <div 
     className="container  min-h-screen flex flex-col justify-center items-center m-0 p-0 min-w-screen bg-gradient-to-br from-[#7c42da] via-[#d11bb4] to-[#e5e500]">
        <div className="poll">
            {/* <question styleName={}></question> */}
            <div 
            className="question-box bg-black w-75 md:w-150 lg:w-180 text-white border px-6 py-10 wrap-break-word text-center text-3xl rounded-t-xl cursor-pointer  border-black ">
                {question}
            </div>
            {/* options... */}
             <div className="options border-white w-75 md:w-150 lg:w-180 border rounded-b-xl py-6 m-0 bg-gray-100">
               {
                options.map((item,key) => {
                    return (
                      <div 
                      key={key} 
                      id={key}
                      onClick={(e) => { 
                        setIsUserSelected(true);
                        setUserSelectedOption(e.target.id);
                      }}  
                      className="text-black text-2xl hover:bg-gray-300 shadow-xl outline-gray-300 bg-gray-200 border transition border-gray-200 mx-10 my-2 rounded-2xl px-12 py-6 cursor-pointer">{item}</div>
                    ); 
                })
               }
             </div>
        </div>
     </div>
  )
}
