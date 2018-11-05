import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(filterName = "UserAuthenticationFilter",
urlPatterns = {"/*"},
initParams = {
    @WebInitParam(/*need to pass in session here*/)})
public class UserAuthenticationFilter implements Filter {
    init() {
    
    }
    
    destroy() {
    
    }
    
    doFilter() {
    
    }
}
