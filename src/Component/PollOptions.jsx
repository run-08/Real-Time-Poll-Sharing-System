import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import shine from "../assets/shine.svg";
export const PollOptions = ({question}) => {
    const[options,setOptions] = useState([]);
    const[data,setData] = useState("")
    const[isAddOption,setIsAdOption]= useState();
   const navigate = useNavigate();
   const token = localStorage.getItem("token");
  useEffect(()=>{
         const token = localStorage.getItem("token");
          if(token === undefined || token === null){
          navigate("/signup");
          return;
      }
    },[]);
    return (
        <div className="">
            <div className="Question  px-3 py-2 rounded-mid opacity-80">
                <span className="font-bold text-white text-2xl">
                    {question}
                </span>
            </div> 
            {options.length > 0  && 
            <div>
                 <div className="container mt-5 px-2">
                    <div className="option">
                       {options.map((item,key) =>(
                          <div  
                          key={key}
                            className="my-2 cursor-pointer border-3 py-3 rounded-xl text-black bg-white  px-5  border-white text-xl outline-white  "
                        >
                            <span>{item}</span>
                        </div>
                       )) }
                    </div>
                  </div>
                </div>
            } 
             {isAddOption && 
              <div className="container mt-5 px-2">
                  <div className="option">
                       <input 
                         onChange={(e) => setData(e.target.value)}
                         type="text" 
                         className="my-2 border-3 py-3 rounded-xl text-black bg-white  px-5  border-white text-xl outline-white  " />
                    </div>
                <div className="border-white rounded-lg cursor-pointer border w-20 px-2 py-1 text-center  bg-white font-bold "
                onClick={() => {
                    if(data.trim().length    == 0){
                        console.log(data);
                        return;
                    }
                    setOptions((prev) => [...prev,data]);
                    setData("");
                    setIsAdOption(false);
                }}>Done</div>
                </div>
             }
              <div className="options-question my-5 flex gap-2">
                <div 
                className="border-2  rounded-xl px-3 mx-2 hover:bg-pink-400 py-2 border-white-400  text-white font-bold  cursor-pointer"
                onClick={() => setIsAdOption(true)}
                >   
                   +Add Options
                </div>
               <div className="border-2 cursor-pointer flex py-2 border-white text-white font-bold px-3  rounded-xl ">
                   
                    <span
                    onClick={()=>navigate("poll/123",{
                        state:{
                            question:question,
                            options:options,
                        }
                    })}
                     className="text-white">Create Poll</span>
                     <img 
                    src={shine} 
                    className=" text-blue max-h-5 pt-1 pl-2  overflow-hidden"></img>
                </div>
            </div>
        </div>
    )
}