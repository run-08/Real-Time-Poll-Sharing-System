import { Route, Routes } from "react-router-dom";
import SignUp from "../Authentication/Signup";
import { HomePage } from "../Component/HomePage";
import { Poll } from "../Component/Poll";
export const AppRouter = () => {
    return (
       <Routes>
          <Route path="/" element={<HomePage></HomePage>}>
          </Route> 
          <Route path="/poll/:pollId" element={<Poll></Poll>}></Route> 
          <Route path="/signup" element={<SignUp></SignUp>}></Route>
       </Routes>
    );
}