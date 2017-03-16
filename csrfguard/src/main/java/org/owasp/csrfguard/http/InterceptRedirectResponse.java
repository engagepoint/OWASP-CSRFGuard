package org.owasp.csrfguard.http;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.owasp.csrfguard.CsrfGuard;

/**
 * Validation has been added to original filter. It checks if token exists already then duplicate token is not added
 */
public class InterceptRedirectResponse extends HttpServletResponseWrapper {

	private HttpServletResponse response = null;

	private CsrfGuard csrfGuard;

	private HttpServletRequest request;

	public InterceptRedirectResponse(HttpServletResponse response, HttpServletRequest request, CsrfGuard csrfGuard) {
		super(response);
		this.response = response;
		this.request = request;
		this.csrfGuard = csrfGuard;
	}

	@Override
	public void sendRedirect(String location) throws IOException {
		// Remove CR and LF characters to prevent CRLF injection
		String sanitizedLocation = location.replaceAll("(\\r|\\n|%0D|%0A|%0a|%0d)", "");
		
		/** ensure token included in redirects **/
		if (!sanitizedLocation.contains("://") && csrfGuard.isProtectedPageAndMethod(sanitizedLocation, "GET")) {
			/** update tokens **/
			csrfGuard.updateTokens(request);
			
			StringBuilder sb = new StringBuilder();

			if (!sanitizedLocation.startsWith("/")) {
				sb.append(request.getContextPath() + "/" + sanitizedLocation);
			} else {
				sb.append(sanitizedLocation);
			}

			// remove any query parameters from the sanitizedLocation
			String locationUri = sanitizedLocation.split("\\?", 2)[0];
			String tokenValue = csrfGuard.getTokenValue(request, locationUri);

			if (tokenValue != null && !sanitizedLocation.contains(tokenValue)) {
				if (sanitizedLocation.contains("?")) {
					sb.append('&');
				} else {
					sb.append('?');
				}

				sb.append(csrfGuard.getTokenName());
				sb.append('=');

				sb.append(tokenValue);
			}
			
			response.sendRedirect(sb.toString());
		} else {
			response.sendRedirect(sanitizedLocation);
		}
	}
}
