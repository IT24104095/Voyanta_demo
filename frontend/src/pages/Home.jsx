import React from 'react';
import { Link } from 'react-router-dom';
import { Container, Row, Col, Button, Card } from 'react-bootstrap';

const Home = () => {
  return (
    <div>
      {/* Hero Section */}
      <section className="hero-section">
        <Container>
          <Row className="align-items-center">
            <Col lg={6}>
              <h1 className="display-4 fw-bold mb-4">
                Welcome to Voyanta
              </h1>
              <p className="lead mb-4">
                Your premier destination for vehicle rentals. Choose from our wide selection 
                of cars, SUVs, and more. Book online and enjoy a seamless rental experience.
              </p>
              <div className="d-flex gap-3">
                <Button as={Link} to="/vehicles" variant="light" size="lg">
                  Browse Vehicles
                </Button>
                <Button as={Link} to="/register" variant="outline-light" size="lg">
                  Get Started
                </Button>
              </div>
            </Col>
            <Col lg={6}>
              <div className="text-center">
                <i className="bi bi-car-front feature-icon"></i>
              </div>
            </Col>
          </Row>
        </Container>
      </section>

      {/* Features Section */}
      <section className="py-5">
        <Container>
          <Row className="text-center mb-5">
            <Col>
              <h2 className="display-5 fw-bold">Why Choose Voyanta?</h2>
              <p className="lead text-muted">
                We provide the best vehicle rental experience with premium service
              </p>
            </Col>
          </Row>
          
          <Row className="g-4">
            <Col md={4}>
              <Card className="h-100 border-0 shadow-sm">
                <Card.Body className="text-center p-4">
                  <i className="bi bi-shield-check feature-icon mb-3"></i>
                  <h5>Safe & Reliable</h5>
                  <p className="text-muted">
                    All our vehicles are regularly maintained and insured for your safety.
                  </p>
                </Card.Body>
              </Card>
            </Col>
            
            <Col md={4}>
              <Card className="h-100 border-0 shadow-sm">
                <Card.Body className="text-center p-4">
                  <i className="bi bi-clock feature-icon mb-3"></i>
                  <h5>24/7 Support</h5>
                  <p className="text-muted">
                    Our customer support team is available round the clock to assist you.
                  </p>
                </Card.Body>
              </Card>
            </Col>
            
            <Col md={4}>
              <Card className="h-100 border-0 shadow-sm">
                <Card.Body className="text-center p-4">
                  <i className="bi bi-credit-card feature-icon mb-3"></i>
                  <h5>Easy Payment</h5>
                  <p className="text-muted">
                    Multiple payment options with secure and hassle-free transactions.
                  </p>
                </Card.Body>
              </Card>
            </Col>
          </Row>
        </Container>
      </section>

      {/* CTA Section */}
      <section className="bg-light py-5">
        <Container>
          <Row className="text-center">
            <Col>
              <h2 className="display-6 fw-bold mb-3">Ready to Get Started?</h2>
              <p className="lead text-muted mb-4">
                Join thousands of satisfied customers who trust Voyanta for their vehicle rental needs.
              </p>
              <Button as={Link} to="/vehicles" variant="primary" size="lg">
                Browse Our Fleet
              </Button>
            </Col>
          </Row>
        </Container>
      </section>
    </div>
  );
};

export default Home;
