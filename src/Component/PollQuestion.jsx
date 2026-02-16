import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { PollOptions } from "./PollOptions";
export const PollQuestion = () => {
   const [isQuestion,setIsQuestion] = useState(false);
   const[question,setQuestion] = useState(null);
   const[isOption,setIsOption] = useState(false);
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
      <div>
      <div className={`poll-container ${isOption ? `hidden`:`block`}`}>
          <textarea
          type="text" 
          placeholder="Type your question here..."
          max={60}
          min={5}
          className="text-white rounded border-2 py-1 md:w-100 h-30 text-2xl px-2 rounded-t-xl  border-white outline-1 outline-white my-1"
          onChange={(e) => {
            setIsQuestion(e.target.value.trim().length > 0)
            setQuestion(e.target.value.trim())
         }}
          >
          </textarea>
          <div className={`optionsPath ${isQuestion ? "block":"hidden"} my-2`}>
            <span 
            className="border bg-gray-100 hover:opacity-70 px-2 py-2 rounded-xl outline-white border-white cursor-pointer text-black font-bold"
            onClick={()=>{
                  if(isQuestion && question.length > 0){
                     setIsOption(true);
                  }
                  else{
                     alert("Enter Question!")
                  }
               }}
            >
               Submit
            </span>
          </div>
       </div>
       {isOption && <PollOptions question={question}/>}
       </div>
       
    );
} 