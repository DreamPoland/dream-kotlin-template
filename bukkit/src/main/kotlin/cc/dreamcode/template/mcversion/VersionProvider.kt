package cc.dreamcode.template.mcversion

import cc.dreamcode.template.mcversion.api.VersionAccessor
import cc.dreamcode.template.mcversion.v1_12_R1.V1_12_R1_VersionAccessor
import cc.dreamcode.template.mcversion.v1_16_R3.V1_16_R3_VersionAccessor
import cc.dreamcode.template.mcversion.v1_17_R1.V1_17_R1_VersionAccessor
import cc.dreamcode.template.mcversion.v1_18_R2.V1_18_R2_VersionAccessor
import cc.dreamcode.template.mcversion.v1_19_R2.V1_19_R2_VersionAccessor
import cc.dreamcode.template.mcversion.v1_8_R3.V1_8_R3_VersionAccessor
import com.cryptomorin.xseries.ReflectionUtils

object VersionProvider {
    val versionAccessor: VersionAccessor
        get() = when (ReflectionUtils.VERSION) {
            "v1_8_R3" -> {
                V1_8_R3_VersionAccessor()
            }
            "v1_12_R1" -> {
                V1_12_R1_VersionAccessor()
            }
            "v1_16_R3" -> {
                V1_16_R3_VersionAccessor()
            }
            "v1_17_R1" -> {
                V1_17_R1_VersionAccessor()
            }
            "v1_18_R2" -> {
                V1_18_R2_VersionAccessor()
            }
            "v1_19_R2" -> {
                V1_19_R2_VersionAccessor()
            }
            else -> {
                throw RuntimeException("Plugin doesn't support this server version, update to: 1.8.8, 1.12.2, 1.16.5, 1.17.1, 1.18.2 or 1.19.3.")
            }
        }
}