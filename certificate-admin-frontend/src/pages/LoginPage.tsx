import { useState } from "react";
import { post } from "../apis/Api";
import { useNavigate } from "react-router-dom";

export default function PlatformAdminLogin() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [showPassword, setShowPassword] = useState(false);
    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);

    const navigate = useNavigate();

    const handleSubmit = async (e: any) => {
        e.preventDefault();

        if (!email.trim() || !password) {
            setError("Enter both email and password.");
            return;
        }

        setError("");
        setLoading(true);

        const result = await post('http://localhost:8080/api/auth/admin', { email: email, password: password });

        if(result.status_code === 200 && result.success) {
            navigate('/admin/schools');
        } else {
            setError("error happens for a reason");
        }

        setLoading(false);
        
    };

    return (
        <div className="min-h-screen flex items-center justify-center bg-gray-50 p-8">
            <div className="w-96 bg-white border border-gray-200 rounded-xl p-10">
                <div className="flex items-center gap-2.5 mb-8">
                    <div className="w-9 h-9 rounded-lg bg-gray-900 flex items-center justify-center">
                        <i className="ti ti-certificate text-white text-lg" aria-hidden="true" />
                    </div>
                    <div>
                        <p className="text-sm font-medium text-gray-900">CertiTrust</p>
                        <p className="text-xs text-gray-400">Platform admin</p>
                    </div>
                </div>

                <h1 className="text-xl font-medium text-gray-900 mb-1">Sign in</h1>
                <p className="text-sm text-gray-500 mb-6">Manage school registration requests.</p>

                <form onSubmit={handleSubmit} className="flex flex-col gap-3.5">
                    <div>
                        <label htmlFor="email" className="text-sm text-gray-500 block mb-1.5">Email</label>
                        <input
                            id="email"
                            type="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            placeholder="admin@certitrust.app"
                            className="w-full border border-gray-200 rounded-md px-3 py-2 text-sm outline-none focus:border-gray-400"
                        />
                    </div>

                    <div>
                        <label htmlFor="password" className="text-sm text-gray-500 block mb-1.5">Password</label>
                        <div className="relative">
                            <input
                                id="password"
                                type={showPassword ? "text" : "password"}
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                placeholder="Enter your password"
                                className="w-full border border-gray-200 rounded-md px-3 py-2 pr-9 text-sm outline-none focus:border-gray-400"
                            />
                            <button
                                type="button"
                                onClick={() => setShowPassword((v) => !v)}
                                aria-label={showPassword ? "Hide password" : "Show password"}
                                className="absolute right-2 top-1/2 -translate-y-1/2 text-gray-400"
                            >
                                <i className={`ti ${showPassword ? "ti-eye-off" : "ti-eye"} text-base`} aria-hidden="true" />
                            </button>
                        </div>
                    </div>

                    {error && <p className="text-sm text-red-600 m-0">{error}</p>}

                    <button
                        type="submit"
                        disabled={loading}
                        className="w-full bg-gray-900 text-white rounded-md py-2 text-sm font-medium hover:bg-gray-800 transition-colors disabled:opacity-50 mt-1"
                    >
                        {loading ? "Signing in…" : "Sign in"}
                    </button>
                </form>

                <p className="text-xs text-gray-400 text-center mt-5">
                    Access is restricted to authorized CertiTrust team members.
                </p>
            </div>
        </div>
    );
}