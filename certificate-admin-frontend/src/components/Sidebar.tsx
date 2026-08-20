import { NavLink } from "react-router-dom";

const navItems = [
    { to: "/", label: "Overview", icon: "ti-layout-dashboard" },
    { to: "/admin/schools", label: "School requests", icon: "ti-building" },
    { to: "/admin/diplomas", label: "Diplomas", icon: "ti-certificate" },
    { to: "/admin/settings", label: "Settings", icon: "ti-settings" },
];

export default function Sidebar() {
    return (
        <aside
            style={{
                width: 220,
                flexShrink: 0,
                height: "100vh",
                borderRight: "0.5px solid #ddd",
                padding: "20px 12px",
                boxSizing: "border-box",
                display: "flex",
                flexDirection: "column",
                gap: 4,
            }}
        >
            <p style={{ fontSize: 15, fontWeight: 500, margin: "0 8px 20px" }}>
                CertiTrust
            </p>

            {navItems.map((item) => (
                <NavLink
                    key={item.to}
                    to={item.to}
                    end={item.to === "/"}
                    style={({ isActive }) => ({
                        display: "flex",
                        alignItems: "center",
                        gap: 10,
                        padding: "8px 10px",
                        borderRadius: 8,
                        fontSize: 14,
                        textDecoration: "none",
                        color: isActive ? "#0C447C" : "#333",
                        background: isActive ? "#E6F1FB" : "transparent",
                    })}
                >
                    <i className={`ti ${item.icon}`} style={{ fontSize: 18 }} aria-hidden="true" />
                    {item.label}
                </NavLink>
            ))}
        </aside>
    );
}