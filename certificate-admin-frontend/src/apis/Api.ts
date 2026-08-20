import type { GlobalResponse } from "../interface/interfaces";

export async function post(url: string, body: any): Promise<GlobalResponse> {
    
    const result = await fetch(url, {
        method: 'POST',
        body: JSON.stringify(body),
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        }
    });

    const response: GlobalResponse = await result.json();

    return response;

}

export async function get(url: string): Promise<GlobalResponse> {

    const result = await fetch(url, {
        method: 'GET',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        }
    });

    const response: GlobalResponse = await result.json();

    return response;

}