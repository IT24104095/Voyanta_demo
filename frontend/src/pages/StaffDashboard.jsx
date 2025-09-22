import React from 'react';
import { Container, Row, Col, Card } from 'react-bootstrap';

const StaffDashboard = () => {
  return (
    <Container className="py-4">
      <Row>
        <Col>
          <h2>Staff Dashboard</h2>
          <p className="text-muted">Manage bookings and assist customers</p>
        </Col>
      </Row>

      <Row className="g-4">
        <Col md={6}>
          <Card>
            <Card.Body className="text-center">
              <i className="bi bi-calendar-check display-4 text-primary mb-3"></i>
              <h5>Booking Management</h5>
              <p className="text-muted">Confirm and manage customer bookings</p>
            </Card.Body>
          </Card>
        </Col>
        
        <Col md={6}>
          <Card>
            <Card.Body className="text-center">
              <i className="bi bi-credit-card display-4 text-success mb-3"></i>
              <h5>Payment Processing</h5>
              <p className="text-muted">Process payments and issue receipts</p>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Container>
  );
};

export default StaffDashboard;
