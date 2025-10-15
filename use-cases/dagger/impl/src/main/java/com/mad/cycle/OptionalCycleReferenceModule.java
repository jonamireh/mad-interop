package com.mad.cycle;

import dagger.BindsOptionalOf;
import dagger.Module;

@Module
public interface OptionalCycleReferenceModule {
  @BindsOptionalOf
  CycleReference optionalCycleReference();
}
