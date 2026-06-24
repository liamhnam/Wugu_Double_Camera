package kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public final class ContextReceiver extends AbstractReceiverValue implements ImplicitReceiver {
    private final CallableDescriptor declarationDescriptor;

    public CallableDescriptor getDeclarationDescriptor() {
        return this.declarationDescriptor;
    }

    public ContextReceiver(CallableDescriptor declarationDescriptor, KotlinType receiverType, ReceiverValue receiverValue) {
        super(receiverType, receiverValue);
        Intrinsics.checkNotNullParameter(declarationDescriptor, "declarationDescriptor");
        Intrinsics.checkNotNullParameter(receiverType, "receiverType");
        this.declarationDescriptor = declarationDescriptor;
    }

    public String toString() {
        return "Cxt { " + getDeclarationDescriptor() + " }";
    }
}
