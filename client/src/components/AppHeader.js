import { Header, Image } from "semantic-ui-react";
import MakeADrinkLogo from '../assets/MakeADrinkLogo.png';

function AppHeader(){


    return(
        <>
         <Header className="recipeHeader" textAlign="center"  as='h2'>
          <Image circular src={MakeADrinkLogo} className='logo' /> 
        </Header>
        </>
    )


}



export default AppHeader;