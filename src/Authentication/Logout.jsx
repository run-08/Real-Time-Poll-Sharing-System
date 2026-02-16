import { useNavigate } from "react-router-dom";

export const Logout = () => {
    const navigate = useNavigate();
    return (
        <div className="container  min-h-screen flex flex-col justify-center items-center m-0 p-0 min-w-screen bg-gradient-to-br from-[#7c42da] via-[#d11bb4] to-[#e5e500]">
            <div className="logout-box justify-center flex flex-col items-center outline-white rounded-xl w-70 md:w-100 h-100 bg-gray-100">
                <span className="text-black border px-2 py-2 rounded-xl text-2xl border-white bg-gray-200">
                    Are you Sure to Logout?
                </span>
                <div className="px-20 grid grid-cols-2 gap-4 my-4">
                   <span 
                   onClick={() => {
                    localStorage.removeItem("token");
                    navigate("/signup")}
                   }
                   className="border border-white text-2xl outline-white hover:opacity-70 bg-red-500 text-white px-4 py-2 rounded-xl cursor-pointer"> 
                   Yes 
                   </span>
                   <span 
                   onClick={() => navigate("/")}
                   className="border border-white text-2xl outline-white hover:opacity-80 bg-green-500 text-white px-4 py-2 rounded-xl cursor-pointer"> No </span>
                </div>
            </div>
        </div>
    );
    
}
