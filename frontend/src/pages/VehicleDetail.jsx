import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import { Container, Row, Col, Card, Button, Spinner, Alert } from 'react-bootstrap';
import { vehicleService } from '../services/vehicleService';
import { useAuth } from '../contexts/AuthContext';

const VehicleDetail = () => {
  const { id } = useParams();
  const { user } = useAuth();
  const [vehicle, setVehicle] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    loadVehicle();
  }, [id]);

  const loadVehicle = async () => {
    try {
      const data = await vehicleService.getVehicleById(id);
      setVehicle(data);
    } catch (error) {
      setError('Failed to load vehicle details');
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
    return (
      <Container className="py-5 text-center">
        <Spinner animation="border" role="status">
          <span className="visually-hidden">Loading...</span>
        </Spinner>
      </Container>
    );
  }

  if (error || !vehicle) {
    return (
      <Container className="py-5">
        <Alert variant="danger">{error || 'Vehicle not found'}</Alert>
      </Container>
    );
  }

  return (
    <Container className="py-4">
      <Row>
        <Col lg={8}>
          <Card>
            {vehicle.imageUrl && (
              <Card.Img 
                variant="top" 
                src={vehicle.imageUrl} 
                alt={`${vehicle.make} ${vehicle.model}`}
                style={{ height: '400px', objectFit: 'cover' }}
              />
            )}
            <Card.Body>
              <h2>{vehicle.make} {vehicle.model}</h2>
              <p className="text-muted mb-3">
                {vehicle.year} • {vehicle.color} • {vehicle.type} • {vehicle.licensePlate}
              </p>
              
              {vehicle.description && (
                <p className="mb-4">{vehicle.description}</p>
              )}

              <div className="mb-4">
                <h5>Pricing</h5>
                <Row>
                  <Col md={6}>
                    <div className="p-3 bg-light rounded">
                      <h6>Daily Rate</h6>
                      <h4 className="text-primary">${vehicle.dailyRate}</h4>
                    </div>
                  </Col>
                  <Col md={6}>
                    <div className="p-3 bg-light rounded">
                      <h6>Hourly Rate</h6>
                      <h4 className="text-primary">${vehicle.hourlyRate}</h4>
                    </div>
                  </Col>
                </Row>
              </div>

              {vehicle.averageRating && (
                <div className="mb-4">
                  <h5>Customer Rating</h5>
                  <div className="d-flex align-items-center">
                    <i className="bi bi-star-fill text-warning me-2"></i>
                    <span className="h5 mb-0 me-2">{vehicle.averageRating.toFixed(1)}</span>
                    <small className="text-muted">({vehicle.totalFeedbacks} reviews)</small>
                  </div>
                </div>
              )}
            </Card.Body>
          </Card>
        </Col>
        
        <Col lg={4}>
          <Card>
            <Card.Body>
              <h5>Book This Vehicle</h5>
              <p className="text-muted mb-3">
                Ready to book this vehicle? Click below to start your reservation.
              </p>
              
              {user ? (
                <Button 
                  as={Link} 
                  to={`/book/${vehicle.id}`} 
                  variant="primary" 
                  size="lg" 
                  className="w-100"
                >
                  Book Now
                </Button>
              ) : (
                <div>
                  <Button 
                    as={Link} 
                    to="/login" 
                    variant="primary" 
                    size="lg" 
                    className="w-100 mb-2"
                  >
                    Login to Book
                  </Button>
                  <p className="text-center text-muted">
                    <small>Don't have an account? <Link to="/register">Sign up here</Link></small>
                  </p>
                </div>
              )}
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Container>
  );
};

export default VehicleDetail;
