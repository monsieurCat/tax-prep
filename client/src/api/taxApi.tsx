const API_URL = 'http://localhost:8282';



export const postTaxInfo = async (personalInfo: any) => {
    const response = await fetch(`${API_URL}/tax_info/full`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        },
        credentials: 'include', 
        body: JSON.stringify(personalInfo)
    });
    if (!response.ok) {
        throw new Error('Failed to post tax info');
    }
    return await response.json();
};


export const fetchTaxInfo = async () => {
    const response = await fetch(`${API_URL}/tax_info/full`, {
        method: 'GET',
        credentials: 'include', // cookies are sent with the request
        headers: {
            'Content-Type': 'application/json'
        }
    });
    if (response.ok) {
        return response.json();
    } else {
        throw new Error('Failed to fetch tax info');
    }
};

export const fetchAddress = async () => {
    const response = await fetch(`${API_URL}/address`, {
        method: 'GET',
        credentials: 'include', 
        headers: {
            'Content-Type': 'application/json'
        }
    });
    if (response.ok) {
        return response.json();
    } else {
        throw new Error('Failed to fetch address');
    }
};

export const updateAddress = async (addressData: any) => {
    const response = await fetch(`${API_URL}/address`, {
        method: 'PUT',
        credentials: 'include', 
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(addressData)
    });
    if (response.ok) {
        const data = await response.json();
        return { status: response.status, data: data }; 
    } else {
        throw new Error('Failed to update address');
    }
};


//create account

export const registerNewUser = async (userData: any) => {
    try {
        const response = await fetch(`${API_URL}/api/auth/register`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
            },
            body: JSON.stringify(userData)
        });
        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || 'Failed to register');
        }
        return await response.json(); 
    } catch (error) {
        throw error; 
    }
};
