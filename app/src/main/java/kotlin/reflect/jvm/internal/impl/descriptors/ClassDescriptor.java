package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.Collection;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;

public interface ClassDescriptor extends ClassOrPackageFragmentDescriptor, ClassifierDescriptorWithTypeParameters {
    ClassDescriptor mo3062getCompanionObjectDescriptor();

    Collection<ClassConstructorDescriptor> getConstructors();

    @Override
    DeclarationDescriptor getContainingDeclaration();

    List<ReceiverParameterDescriptor> getContextReceivers();

    List<TypeParameterDescriptor> getDeclaredTypeParameters();

    @Override
    SimpleType getDefaultType();

    InlineClassRepresentation<SimpleType> getInlineClassRepresentation();

    ClassKind getKind();

    MemberScope getMemberScope(TypeSubstitution typeSubstitution);

    Modality getModality();

    @Override
    ClassDescriptor getOriginal();

    Collection<ClassDescriptor> getSealedSubclasses();

    MemberScope getStaticScope();

    ReceiverParameterDescriptor getThisAsReceiverParameter();

    MemberScope getUnsubstitutedInnerClassesScope();

    MemberScope getUnsubstitutedMemberScope();

    ClassConstructorDescriptor mo3063getUnsubstitutedPrimaryConstructor();

    DescriptorVisibility getVisibility();

    boolean isCompanionObject();

    boolean isData();

    boolean isFun();

    boolean isInline();

    boolean isValue();
}
