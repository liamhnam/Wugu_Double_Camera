package org.apache.http.impl.entity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpMessage;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.p042io.SessionInputBuffer;
import org.apache.http.util.Args;

@Deprecated
public class EntityDeserializer {
    private final ContentLengthStrategy lenStrategy;

    public EntityDeserializer(ContentLengthStrategy contentLengthStrategy) {
        this.lenStrategy = (ContentLengthStrategy) Args.notNull(contentLengthStrategy, "Content length strategy");
    }

    public HttpEntity deserialize(SessionInputBuffer sessionInputBuffer, HttpMessage httpMessage) {
        Args.notNull(sessionInputBuffer, "Session input buffer");
        Args.notNull(httpMessage, "HTTP message");
        return doDeserialize(sessionInputBuffer, httpMessage);
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected org.apache.http.entity.BasicHttpEntity doDeserialize(org.apache.http.p042io.SessionInputBuffer r8, org.apache.http.HttpMessage r9) {
        /*
            r7 = this;
            org.apache.http.entity.BasicHttpEntity r0 = new org.apache.http.entity.BasicHttpEntity
            r0.<init>()
            org.apache.http.entity.ContentLengthStrategy r1 = r7.lenStrategy
            long r1 = r1.determineLength(r9)
            r3 = -2
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            r4 = -1
            if (r3 != 0) goto L23
            r1 = 1
            r0.setChunked(r1)
            r0.setContentLength(r4)
            org.apache.http.impl.io.ChunkedInputStream r1 = new org.apache.http.impl.io.ChunkedInputStream
            r1.<init>(r8)
        L1f:
            r0.setContent(r1)
            goto L3f
        L23:
            int r3 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            r6 = 0
            r0.setChunked(r6)
            if (r3 != 0) goto L34
            r0.setContentLength(r4)
            org.apache.http.impl.io.IdentityInputStream r1 = new org.apache.http.impl.io.IdentityInputStream
            r1.<init>(r8)
            goto L1f
        L34:
            r0.setContentLength(r1)
            org.apache.http.impl.io.ContentLengthInputStream r3 = new org.apache.http.impl.io.ContentLengthInputStream
            r3.<init>(r8, r1)
            r0.setContent(r3)
        L3f:
            java.lang.String r8 = "Content-Type"
            org.apache.http.Header r8 = r9.getFirstHeader(r8)
            if (r8 == 0) goto L4a
            r0.setContentType(r8)
        L4a:
            java.lang.String r8 = "Content-Encoding"
            org.apache.http.Header r8 = r9.getFirstHeader(r8)
            if (r8 == 0) goto L55
            r0.setContentEncoding(r8)
        L55:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.http.impl.entity.EntityDeserializer.doDeserialize(org.apache.http.io.SessionInputBuffer, org.apache.http.HttpMessage):org.apache.http.entity.BasicHttpEntity");
    }
}
