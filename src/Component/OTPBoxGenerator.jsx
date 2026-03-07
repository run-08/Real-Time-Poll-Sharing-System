const OTPBoxGenerator = ({ idx, inputs, handleKey, userOTP,setUserOTP}) => {
  // console.log(userOTP);
  // console.log(setUserOTP);
  
  return (
    <div 
    className="OTP-digit-box ">
        <input
        key={idx}
          ref={(el) => (inputs.current[idx] = el)}
        onKeyDown={(e) => handleKey(e, idx)}
        onChange={(e) =>{
            const value = e.target.value;
            setUserOTP((prev) => {
              const newOTP = [...prev];
              newOTP[idx] = value;
              console.log(newOTP);
              return newOTP;
            });
        }}
        maxLength="1"
         className="outline-2 bg-gray-300 cursor-pointer my-2 text-3xl  w-13 text-center font-bold h-13 rounded-xl outline-transparent  "></input>
    </div>
  )
}
export default OTPBoxGenerator;