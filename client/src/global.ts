const VITE_API_HOST = import.meta.env.VITE_API_HOST;

export const API_URL = VITE_API_HOST && `https://${VITE_API_HOST}.onrender.com`;
