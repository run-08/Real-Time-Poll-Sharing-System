import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

export const UserPoll = () => {
    const[userPoll,setUserPoll] = useState([]);
    const navigate = useNavigate();
    useEffect(()=>{
        const token = localStorage.getItem("token");
        if(token === undefined || token === null){
         navigate("/signup");
         return;
        }
        const fetchUserPoll = async ()=>{
        try{
            const response = await fetch("http://localhost:1001/api/getUserPoll?pollID=aru701567@gmail.com",{
                method:"GET",
                headers:{
                    'Content-Type':'application/json'
                },
            });
            if(response.ok){
                const data = await response.json();
                setUserPoll(data);
                console.log(data);
                data.map((item,key) => {
                   console.log(item.question);
                });    
            }
        } 
        catch(e){
          console.log(e);
        }
    };
    fetchUserPoll();
    },[]);
    return(
      <div className="container min-h-screen flex flex-col justify-center items-center m-0 p-0 min-w-screen bg-gradient-to-br from-[#7c42da] via-[#d11bb4] to-[#e5e500]">
           <div className="grid  xl:grid-cols-2 gap-10 py-20 my-4">
            {   
               userPoll.map((items,key) => (
                   <div className="poll  px-20 hover:scale-y-115 transition-all">
            {/* <question styleName={}></question> */}
            <div  key={key}
            className="question-box   bg-black w-75 md:w-150 lg:w-180 text-white border px-6 py-10 wrap-break-word text-center text-3xl rounded-t-xl cursor-pointer  border-black ">
                {items?.question}
            </div>
            {/* options... */}
             <div 
             key={key}
             className="options  border-white w-75 md:w-150 lg:w-180 border rounded-br-xl py-6 m-0 bg-gray-100">
               {
                items?.options?.split(", ").map((item,key) => {
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
             <div className="dateAndTime  rounded-b-2xl  outline-white outline-2 text-white text-xl max-w-100 bg-[#3333ff]">
                 <span className="border italic text-2xl font-bold outline-2 outline-white px-2  bg-white text-blue-900 rounded-br-xl">
                       CREATED AT
                    </span>
                <div className="dataBox text-center text-2xl">
                   
                    {items?.time.substring(0,10)}
                </div>
             </div>
        </div>  
               ))
            }
           </div>
       </div>
    );
}