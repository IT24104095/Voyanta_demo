import React from 'react';
import { Container, Row, Col, Card } from 'react-bootstrap';

const AdminDashboard = () => {
  return (
    <Container className="py-4">
      <Row>
        <Col>
          <h2>Admin Dashboard</h2>
          <p className="text-muted">Manage vehicles, users, and system settings</p>
        </Col>
      </Row>

      <Row className="g-4">
        <Col md={4}>
          <Card>
            <Card.Body className="text-center">
              <i className="bi bi-car-front display-4 text-primary mb-3"></i>
              <h5>Vehicle Management</h5>
              <p className="text-muted">Add, edit, and manage vehicles</p>
            </Card.Body>
          </Card>
        </Col>
        
        <Col md={4}>
          <Card>
            <Card.Body className="text-center">
              <i className="bi bi-people display-4 text-success mb-3"></i>
              <h5>User Management</h5>
              <p className="text-muted">Manage users and permissions</p>
            </Card.Body>
          </Card>
        </Col>
        
        <Col md={4}>
          <Card>
            <Card.Body className="text-center">
              <i className="bi bi-graph-up display-4 text-info mb-3"></i>
              <h5>Reports & Analytics</h5>
              <p className="text-muted">View system reports and analytics</p>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Container>
  );
};

export default AdminDashboard;
