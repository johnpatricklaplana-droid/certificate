import { Outlet } from "react-router-dom";
import Sidebar from "./components/Sidebar";

export function Root() {
    return (
        <div className="flex">
            <Sidebar />

            <main className="w-screen">
                <Outlet />
            </main>
        </div>
    );
}