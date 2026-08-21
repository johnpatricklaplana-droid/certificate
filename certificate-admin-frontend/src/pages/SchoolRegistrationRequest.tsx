import { useEffect, useState } from "react";
import { get, post } from "../apis/Api";

interface SchoolRequest {
    id: string;
    name: string;
    email: string;
    address: string;
    website: string;
    logoUrl: string;
}

export default function SchoolRegistrationRequests() {
    const [requests, setRequests] = useState<SchoolRequest[]>([]);
    const [sentIds, setSentIds] = useState<Set<string>>(new Set());

    const handleApprove = async (school: SchoolRequest) => {

        const body = {
            toEmail: school.email,
            schoolName: school.name,
            token: "TODO"
        };
        
        const result = await post('http://localhost:8080/api/platform-admin/email/school', body);

    };

    const handleReject = (id: string) => {
        
        setRequests((prev) =>
            prev.map((r) => (r.id === id ? { ...r, status: "rejected" } : r))
        );
    };

    useEffect(() => {
        
        const getIt = async () => {

            const result = await get('http://localhost:8080/api/platform-admin/school');

            setRequests(result.response_body);

        };

        getIt();

    }, []);

    return (
        <div className="max-w-3xl mx-auto py-8">
            <div className="flex items-center justify-between mb-6">
                <div>
                    <h1 className="text-xl font-medium text-gray-900">Registration requests</h1>
                    <p className="text-sm text-gray-500 mt-1">
                        Review school details, then approve or reject.
                    </p>
                </div>
                <span className="text-xs font-medium px-2.5 py-1 rounded-md bg-blue-50 text-blue-700">
                    {requests.length} pending
                </span>
            </div>

            {requests.length === 0 ? (
                <div className="text-center py-16 border border-dashed border-gray-200 rounded-xl">
                    <p className="text-sm font-medium text-gray-900">No pending requests</p>
                    <p className="text-sm text-gray-500 mt-1">
                        Approved and rejected requests are removed from this list.
                    </p>
                </div>
            ) : (
                <div className="border border-gray-200 rounded-xl overflow-hidden bg-white divide-y divide-gray-100">
                    {requests.map((r) => (
                        <div key={r.id} className="p-5 flex gap-4">
                            <img src={r.logoUrl} className="w-10 h-10 shrink-0 rounded-full bg-blue-50 text-blue-700 flex items-center justify-center text-sm font-medium" />

                            <div className="flex-1 min-w-0">
                                <p className="text-sm font-medium text-gray-900">{r.name}</p>

                                <dl className="mt-2 space-y-1.5">
                                    <div className="flex items-start gap-2 text-sm text-gray-500">
                                        <dt className="w-16 shrink-0 text-gray-400">Email</dt>
                                        <dd className="truncate">{r.email}</dd>
                                    </div>
                                    <div className="flex items-start gap-2 text-sm text-gray-500">
                                        <dt className="w-16 shrink-0 text-gray-400">Address</dt>
                                        <dd>{r.address}</dd>
                                    </div>
                                    <div className="flex items-start gap-2 text-sm text-gray-500">
                                        <dt className="w-16 shrink-0 text-gray-400">Website</dt>
                                        <dd className="truncate">{r.website}</dd>
                                    </div>
                                </dl>

                                {sentIds.has(r.id) && (
                                    <p className="mt-3 text-xs font-medium text-teal-700 bg-teal-50 inline-block px-2 py-1 rounded-md">
                                        Verification email sent — awaiting confirmation
                                    </p>
                                )}
                            </div>

                            <div className="flex gap-2 shrink-0 h-fit">
                                <button
                                    onClick={() => handleReject(r.id)}
                                    className="text-sm px-3 py-1.5 rounded-md border border-gray-200 text-gray-700 hover:bg-gray-50 transition-colors"
                                >
                                    Reject
                                </button>
                                <button
                                    onClick={() => handleApprove(r)}
                                    disabled={sentIds.has(r.id)}
                                    className="text-sm px-3 py-1.5 rounded-md border border-blue-200 text-blue-700 hover:bg-blue-50 transition-colors disabled:opacity-50 disabled:hover:bg-transparent"
                                >
                                    {sentIds.has(r.id) ? "Sent" : "Approve"}
                                </button>
                            </div>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
}