package com.p020hp.printsdk;

import java.util.List;
import java.util.UUID;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

public interface InterfaceC1699g {
    Object mo514a(C1687e c1687e, Continuation<? super C1777v3> continuation);

    Object mo515a(UUID uuid, List<String> list, Continuation<? super C1687e> continuation);

    Object mo516a(UUID uuid, Continuation<? super Unit> continuation);

    String mo517a();

    Flow<C1668b> mo518a(C1680d c1680d);

    Flow<C1668b> mo519a(UUID uuid);

    Object mo520b(UUID uuid, Continuation<? super Unit> continuation);

    Flow<C1687e> mo521b(UUID uuid);
}
