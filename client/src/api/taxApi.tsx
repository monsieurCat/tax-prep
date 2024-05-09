import { TaxInfoState } from '../redux/slices/taxSlice';

const API_URL = 'http://localhost:8282';



export const postTaxInfo = async (taxInfo: TaxInfoState) => {
    const response = await fetch(`${API_URL}/tax_info/full`, {
        method: 'POST',
        credentials: 'include', 
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        },
        
        body: JSON.stringify(taxInfo)
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

//personal-form api call
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
//get user info
export const fetchUserInfoApi = async () => {
    const response = await fetch(`${API_URL}/user/info`, {
        method: 'GET',
        credentials: 'include', 
        headers: {
            'Content-Type': 'application/json'
        }
    });
    if (response.ok) {
        return response.json();
    } else {
        throw new Error('Failed to fetch user info');
    }
};

//personal-form api call
export const updateUser = async (userData: any) => {
    const response = await fetch(`${API_URL}/user/update`, {
        method: 'PUT',
        credentials: 'include', 
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        },
        
        body: JSON.stringify(userData)
    });
    if (!response.ok) {
        throw new Error('Failed to update user');
    }
    return await response.json();
};

//TAX CALCULATOR!!!!! 
export const fetchTaxResults = async () => {
    const response = await fetch(`${API_URL}/tax_info/calculate`, {
        method: 'GET',
        credentials: 'include', // cookies are sent with the request
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        }
    });
    if (!response.ok) {
        throw new Error('Failed to fetch tax results');
    }
    return await response.json();
};




//filing status api call
export const updateFilingStatusApi = async (filingStatus: string) => {
    const response = await fetch(`${API_URL}/tax_info/filing_status`, {
        method: 'PUT',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        },
        body: JSON.stringify({ filingStatus })
    });
    if (!response.ok) {
        throw new Error('Failed to update filing status');
    }
    return await response.json();
};

export const fetchFilingStatusApi = async () => {
    const response = await fetch(`${API_URL}/tax_info/filing_status`, { 
        method: 'GET',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        }
    });
    if (!response.ok) {
        throw new Error('Failed to fetch filing status');
    }
    return await response.json();
};

