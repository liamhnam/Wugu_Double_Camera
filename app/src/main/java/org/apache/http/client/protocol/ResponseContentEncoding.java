package org.apache.http.client.protocol;

import com.p020hp.jipp.model.Compression;
import java.util.Locale;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.entity.DeflateDecompressingEntity;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

public class ResponseContentEncoding implements HttpResponseInterceptor {
    public static final String UNCOMPRESSED = "http.client.response.uncompressed";

    @Override
    public void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException {
        Header contentEncoding;
        HttpEntity gzipDecompressingEntity;
        HttpEntity entity = httpResponse.getEntity();
        if (entity == null || entity.getContentLength() == 0 || (contentEncoding = entity.getContentEncoding()) == null) {
            return;
        }
        HeaderElement[] elements = contentEncoding.getElements();
        boolean z = false;
        if (elements.length > 0) {
            HeaderElement headerElement = elements[0];
            String lowerCase = headerElement.getName().toLowerCase(Locale.US);
            if (Compression.gzip.equals(lowerCase) || "x-gzip".equals(lowerCase)) {
                gzipDecompressingEntity = new GzipDecompressingEntity(httpResponse.getEntity());
            } else {
                if (!Compression.deflate.equals(lowerCase)) {
                    if (!HTTP.IDENTITY_CODING.equals(lowerCase)) {
                        throw new HttpException("Unsupported Content-Coding: " + headerElement.getName());
                    }
                    return;
                }
                gzipDecompressingEntity = new DeflateDecompressingEntity(httpResponse.getEntity());
            }
            httpResponse.setEntity(gzipDecompressingEntity);
            z = true;
        }
        if (z) {
            httpResponse.removeHeaders("Content-Length");
            httpResponse.removeHeaders("Content-Encoding");
            httpResponse.removeHeaders(HttpHeaders.CONTENT_MD5);
        }
    }
}
