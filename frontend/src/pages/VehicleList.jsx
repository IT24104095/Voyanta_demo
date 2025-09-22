import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { Container, Row, Col, Card, Form, Button, Spinner, Alert } from 'react-bootstrap';
import { vehicleService } from '../services/vehicleService';
import { toast } from 'react-toastify';

const VehicleList = () => {
  const [vehicles, setVehicles] = useState([]);
  const [filteredVehicles, setFilteredVehicles] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [filters, setFilters] = useState({
    type: '',
    make: '',
    minPrice: '',
    maxPrice: '',
    search: ''
  });
  const [makes, setMakes] = useState([]);

  useEffect(() => {
    loadVehicles();
    loadMakes();
  }, []);

  useEffect(() => {
    applyFilters();
  }, [vehicles, filters]);

  const loadVehicles = async () => {
    try {
      const data = await vehicleService.getAvailableVehicles();
      setVehicles(data);
    } catch (error) {
      setError('Failed to load vehicles');
      toast.error('Failed to load vehicles');
    } finally {
      setLoading(false);
    }
  };

  const loadMakes = async () => {
    try {
      const data = await vehicleService.getAllMakes();
      setMakes(data);
    } catch (error) {
      console.error('Failed to load makes:', error);
    }
  };

  const applyFilters = () => {
    let filtered = [...vehicles];

    if (filters.type) {
      filtered = filtered.filter(v => v.type === filters.type);
    }

    if (filters.make) {
      filtered = filtered.filter(v => v.make === filters.make);
    }

    if (filters.minPrice) {
      filtered = filtered.filter(v => v.dailyRate >= parseFloat(filters.minPrice));
    }

    if (filters.maxPrice) {
      filtered = filtered.filter(v => v.dailyRate <= parseFloat(filters.maxPrice));
    }

    if (filters.search) {
      const searchTerm = filters.search.toLowerCase();
      filtered = filtered.filter(v => 
        v.make.toLowerCase().includes(searchTerm) ||
        v.model.toLowerCase().includes(searchTerm) ||
        v.color.toLowerCase().includes(searchTerm)
      );
    }

    setFilteredVehicles(filtered);
  };

  const handleFilterChange = (e) => {
    setFilters({
      ...filters,
      [e.target.name]: e.target.value
    });
  };

  const clearFilters = () => {
    setFilters({
      type: '',
      make: '',
      minPrice: '',
      maxPrice: '',
      search: ''
    });
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

  if (error) {
    return (
      <Container className="py-5">
        <Alert variant="danger">{error}</Alert>
      </Container>
    );
  }

  return (
    <Container className="py-4">
      <Row>
        <Col>
          <h2 className="mb-4">Available Vehicles</h2>
        </Col>
      </Row>

      {/* Filters */}
      <Card className="mb-4">
        <Card.Body>
          <Row>
            <Col md={3}>
              <Form.Group>
                <Form.Label>Search</Form.Label>
                <Form.Control
                  type="text"
                  name="search"
                  value={filters.search}
                  onChange={handleFilterChange}
                  placeholder="Search vehicles..."
                />
              </Form.Group>
            </Col>
            <Col md={2}>
              <Form.Group>
                <Form.Label>Type</Form.Label>
                <Form.Select
                  name="type"
                  value={filters.type}
                  onChange={handleFilterChange}
                >
                  <option value="">All Types</option>
                  <option value="SEDAN">Sedan</option>
                  <option value="SUV">SUV</option>
                  <option value="HATCHBACK">Hatchback</option>
                  <option value="COUPE">Coupe</option>
                  <option value="CONVERTIBLE">Convertible</option>
                  <option value="TRUCK">Truck</option>
                  <option value="VAN">Van</option>
                  <option value="MOTORCYCLE">Motorcycle</option>
                </Form.Select>
              </Form.Group>
            </Col>
            <Col md={2}>
              <Form.Group>
                <Form.Label>Make</Form.Label>
                <Form.Select
                  name="make"
                  value={filters.make}
                  onChange={handleFilterChange}
                >
                  <option value="">All Makes</option>
                  {makes.map(make => (
                    <option key={make} value={make}>{make}</option>
                  ))}
                </Form.Select>
              </Form.Group>
            </Col>
            <Col md={2}>
              <Form.Group>
                <Form.Label>Min Price</Form.Label>
                <Form.Control
                  type="number"
                  name="minPrice"
                  value={filters.minPrice}
                  onChange={handleFilterChange}
                  placeholder="Min price"
                />
              </Form.Group>
            </Col>
            <Col md={2}>
              <Form.Group>
                <Form.Label>Max Price</Form.Label>
                <Form.Control
                  type="number"
                  name="maxPrice"
                  value={filters.maxPrice}
                  onChange={handleFilterChange}
                  placeholder="Max price"
                />
              </Form.Group>
            </Col>
            <Col md={1} className="d-flex align-items-end">
              <Button variant="outline-secondary" onClick={clearFilters}>
                Clear
              </Button>
            </Col>
          </Row>
        </Card.Body>
      </Card>

      {/* Vehicle Grid */}
      <Row>
        {filteredVehicles.length === 0 ? (
          <Col>
            <Alert variant="info">No vehicles found matching your criteria.</Alert>
          </Col>
        ) : (
          filteredVehicles.map(vehicle => (
            <Col key={vehicle.id} md={6} lg={4} className="mb-4">
              <Card className="h-100 vehicle-card">
                {vehicle.imageUrl && (
                  <Card.Img 
                    variant="top" 
                    src={vehicle.imageUrl} 
                    alt={`${vehicle.make} ${vehicle.model}`}
                    style={{ height: '200px', objectFit: 'cover' }}
                  />
                )}
                <Card.Body>
                  <Card.Title>{vehicle.make} {vehicle.model}</Card.Title>
                  <Card.Text>
                    <small className="text-muted">
                      {vehicle.year} • {vehicle.color} • {vehicle.type}
                    </small>
                    <br />
                    <strong>${vehicle.dailyRate}/day</strong> • ${vehicle.hourlyRate}/hour
                  </Card.Text>
                  {vehicle.averageRating && (
                    <div className="mb-2">
                      <i className="bi bi-star-fill text-warning"></i>
                      <span className="ms-1">{vehicle.averageRating.toFixed(1)}</span>
                      <small className="text-muted ms-1">({vehicle.totalFeedbacks} reviews)</small>
                    </div>
                  )}
                </Card.Body>
                <Card.Footer>
                  <Button as={Link} to={`/vehicles/${vehicle.id}`} variant="primary" className="w-100">
                    View Details
                  </Button>
                </Card.Footer>
              </Card>
            </Col>
          ))
        )}
      </Row>
    </Container>
  );
};

export default VehicleList;
