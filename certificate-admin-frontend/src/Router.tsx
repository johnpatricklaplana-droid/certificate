import { createBrowserRouter } from "react-router-dom";
import PlatformAdminLogin from "./pages/LoginPage";
import SchoolRegistrationRequests from "./pages/SchoolRegistrationRequest";
import { Root } from "./Root";

export const router = createBrowserRouter([

    { path: 'admin/login', Component: PlatformAdminLogin },

    { path: 'admin', 
      Component: Root, 
      children: [
        { path: 'schools', Component: SchoolRegistrationRequests }
      ] 
    },

]);