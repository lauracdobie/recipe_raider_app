import {Card, Icon, Image, Popup, Button, Modal} from 'semantic-ui-react';
import Recipe from './Recipe';
import {useState} from 'react';

// Meal component which renders a meal card component from semantic UI

// Pass the name, area, image parameters from consumed back-end as props to Meal component
function Cocktail({cocktail}){
    const [open, setOpen] = useState(false);

    return (
        <>
        <Card color='pink' className="meal">
                <Image
                    src={cocktail.thumbURL}
                    wrapped
                    ui={false}
                />
                <Card.Content>
                    <Card.Header>{cocktail.name}</Card.Header>
                    <Card.Description>{cocktail.strCategory}</Card.Description>
                    <Card.Description>{cocktail.strDrinkAlcoholic}</Card.Description>
                </Card.Content>
                <Card.Content extra>
                <Modal closeIcon
                        onClose={() => setOpen(false)}
                        onOpen={() => setOpen(true)}
                        open={open} trigger={
                    <a className= "cardContent">
                        <Icon name="glass martini" />
                        Show cocktail recipe
                    </a>}
                >
                <Modal.Content>
                    <Recipe recipe={cocktail}/>
                </Modal.Content>
                </Modal>
                </Card.Content>
            </Card>
    </>
    );



}

export default Cocktail;