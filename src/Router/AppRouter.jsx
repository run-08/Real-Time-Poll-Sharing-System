import { Route, Routes } from "react-router-dom";
import { Logout } from "../Authentication/Logout";
import SignUp from "../Authentication/Signup";
import { HomePage } from "../Component/HomePage";
import { Poll } from "../Component/Poll";
import { ProfilePage } from "../Component/ProfilePage";
import { UserPoll } from "../Component/UserPoll";
export const AppRouter = () => {
    return (
       <Routes>
          <Route path="/" element={<HomePage></HomePage>}>
          </Route> 
          <Route path="/poll/:pollId" element={<Poll></Poll>}></Route> 
          <Route path="/signup" element={<SignUp></SignUp>}></Route>
          <Route path="/logout" element={<Logout></Logout>}></Route>
          <Route path="/userPolls" element={<UserPoll></UserPoll>}></Route>
          <Route path="/profile" element={<ProfilePage></ProfilePage>}></Route>
       </Routes>
    );
}