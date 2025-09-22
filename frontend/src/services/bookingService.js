import api from './api';

export const bookingService = {
  createBooking: async (bookingData) => {
    const response = await api.post('/client/bookings', bookingData);
    return response.data;
  },

  getUserBookings: async () => {
    const response = await api.get('/client/bookings');
    return response.data;
  },

  getBookingById: async (id) => {
    const response = await api.get(`/client/bookings/${id}`);
    return response.data;
  },

  cancelBooking: async (id) => {
    const response = await api.put(`/client/bookings/${id}/cancel`);
    return response.data;
  },

  // Admin/Staff functions
  getAllBookings: async () => {
    const response = await api.get('/admin/bookings');
    return response.data;
  },

  updateBookingStatus: async (id, status) => {
    const response = await api.put(`/admin/bookings/${id}/status?status=${status}`);
    return response.data;
  }
};
