// creates a nav-bar for the user interface

export const Navigation = ({children}) => {
    return(
        <nav className = 'navbar'>
            {children}
        </nav>
    );
}