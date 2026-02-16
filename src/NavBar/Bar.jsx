export const Bar = () => {
   return (
      <div className="navbar h-13 absolute grid grid-cols-2  items-center border-2 border-transparent border-b-gray-200 outline-white min-w-screen m-0 p-0">
        <div className=""></div>
        <div className="pl-20">
            <span
            className="cursor-pointer text-white underline-offset-3 transition hover:underline  rounded-xl text-2xl  px-7" 
            >Your Polls</span>
            <span
            className="cursor-pointer text-white text-2xl py-2 px-7  underline-offset-3 hover:underline" 
            >You Voted</span>
            <span
            className="cursor-pointer text-white text-2xl py-2 px-7  underline-offset-3 hover:underline" 
            >Dashboard</span>
            <span
            className="cursor-pointer text-white text-2xl py-2 px-7  underline-offset-6 hover:underline" 
            >Logout</span>
        </div>
      </div>
   );
}