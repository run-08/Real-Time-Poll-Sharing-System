import { useNavigate } from "react-router-dom";

export const Bar = () => {
   const navigate = useNavigate();
   return (
      <div className="navbar ">
        <div className="flex gap-20 border bg-transparent transition-all border-transparent border-b-white  p-1 justify-end flex-wrap md:float-end">
          <span
            className="cursor-pointer underline-offset-3 transition-all duration-300 hover:underline  rounded-xl text-2xl " 
            onClick={()=>navigate("/")}
            >Home</span>
            <span
            className="cursor-pointer underline-offset-3 transition hover:underline  rounded-xl text-2xl " 
            onClick={()=>navigate("/userPolls")}
            >Your Polls</span>
            <span
            className="cursor-pointer  text-2xl  underline-offset-3 hover:underline" 
            >You Voted</span>
            <span
            className="cursor-pointer text-2xl  underline-offset-3 hover:underline" 
            >
               <a href="/profile">Profile</a></span>
            <span
            className="cursor-pointer  text-2xl   underline-offset-6 hover:underline" 
            ><a href="/logout">Logout</a></span>
        </div>
      </div>
   );
}