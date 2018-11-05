import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(filterName = "UserAuthenticationFilter",
urlPatterns = {"/*"},
initParams = {
    @WebInitParam(/*need to pass in session here*/)})
public class UserAuthenticationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    
    }
    
    doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
    
    }
        
    destroy() {
    
    }
}
