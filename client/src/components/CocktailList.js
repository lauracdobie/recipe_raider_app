import Cocktail from './Cocktail';
import {Card, Container, Pagination} from 'semantic-ui-react'

function CocktailList({cocktails, prevSearch, onPageChange, pageCount, loaded, allCocktails}) {
    // return no cards if there are no cocktails. Otherwise....
    if (!cocktails) return null;
     // logic for only returning valid searches
     if (cocktails && cocktails.length === 0 && prevSearch !== "" && loaded===true) {
      return (
          <Container className="aboutContainer" text>
            <p className="searchMsg"> No cocktail recipes matching {prevSearch.toLowerCase()}</p>
          </Container>
      );
    }
    
    // Map the cocktails in a function called displayCocktails which returns the API key/value pairs
    // we seek from the stored back end recipe API. We render the results in MealList
    const displayCocktails = cocktails.map(cocktail => {
        return <Cocktail
                cocktail={cocktail}/>
    })

    // Pagination is displayed only if there are cocktails from the fetch i.e cocktails.length > 0

    const displayPagination = () => {
        if (allCocktails.length < 6){
            return
        }
        else {
            return (<Pagination className="pagination" defaultActivePage={1} totalPages={pageCount} onPageChange={onPageChange} />)
        }
    }

    // We create a variable to call pagination at the end render return statement

    const pagination = displayPagination();


// we call

    return (
  <section className="recipe-list">
    <Card.Group itemsPerRow={3} className="meal-list">
      {displayCocktails}
    </Card.Group>
    {pagination}
  </section>
        );
}

export default CocktailList;