import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { useNavigate } from 'react-router-dom'
import imgUser from './icons/user-solid.svg'

function NavScol() {
    const navigate = useNavigate();

    return (
        <div >
            <div>
                <img src={imgUser} alt="User Icon" width="32" height="32" />
                <h3>Collège Dakar</h3>
                <h4>Espace Professeur : M. Dev</h4> 
            </div>
            <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
                <Container>
                    <Nav className="me-auto">
                        <Nav.Link onClick={() => navigate("/")}>Mes données</Nav.Link>
                    </Nav>
                    <Nav className="me-auto">
                        <Nav.Link onClick={() => navigate("/")}>Cahier de textes</Nav.Link>
                    </Nav>
                    <Nav className="me-auto">
                        <Nav.Link onClick={() => navigate("/")}>Notes</Nav.Link>
                    </Nav>
                    <Nav className="me-auto">
                        <Nav.Link onClick={() => navigate("/")}>Résultats</Nav.Link>
                    </Nav>
                    <Nav className="me-auto">
                        <Nav.Link onClick={() => navigate("/")}>Vie Scolaire</Nav.Link>
                    </Nav>
                    <Nav className="me-auto">
                        <Nav.Link onClick={() => navigate("/")}>Communication</Nav.Link>
                    </Nav>
                    <Nav>
                        <Nav.Link eventKey={2} href="#memes">
                            Connexion
                        </Nav.Link>
                    </Nav>
                </Container>
            </Navbar>
        </div>

    );
}

export default NavScol;