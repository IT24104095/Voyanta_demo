import api from './api';

export const vehicleService = {
  getAllVehicles: async () => {
    const response = await api.get('/public/vehicles');
    return response.data;
  },

  getAvailableVehicles: async () => {
    const response = await api.get('/public/vehicles/available');
    return response.data;
  },

  getVehicleById: async (id) => {
    const response = await api.get(`/public/vehicles/${id}`);
    return response.data;
  },

  getVehiclesByType: async (type) => {
    const response = await api.get(`/public/vehicles/type/${type}`);
    return response.data;
  },

  getVehiclesByMake: async (make) => {
    const response = await api.get(`/public/vehicles/make/${make}`);
    return response.data;
  },

  searchVehicles: async (query) => {
    const response = await api.get(`/public/vehicles/search?q=${query}`);
    return response.data;
  },

  getVehiclesByPriceRange: async (minRate, maxRate) => {
    const response = await api.get(`/public/vehicles/price-range?minRate=${minRate}&maxRate=${maxRate}`);
    return response.data;
  },

  getAllMakes: async () => {
    const response = await api.get('/public/vehicles/makes');
    return response.data;
  },

  getModelsByMake: async (make) => {
    const response = await api.get(`/public/vehicles/models?make=${make}`);
    return response.data;
  },

  // Admin functions
  createVehicle: async (vehicleData) => {
    const response = await api.post('/admin/vehicles', vehicleData);
    return response.data;
  },

  updateVehicle: async (id, vehicleData) => {
    const response = await api.put(`/admin/vehicles/${id}`, vehicleData);
    return response.data;
  },

  deleteVehicle: async (id) => {
    const response = await api.delete(`/admin/vehicles/${id}`);
    return response.data;
  },

  updateVehicleStatus: async (id, status) => {
    const response = await api.put(`/admin/vehicles/${id}/status?status=${status}`);
    return response.data;
  }
};
