/*
 * Hello Minecraft! Launcher
 * Copyright (C) 2019  huangyuhui <huanghongxun2008@126.com> and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package org.jackhuang.hmcl.download;

import org.jackhuang.hmcl.download.forge.ForgeVersionList;
import org.jackhuang.hmcl.download.game.GameVersionList;
import org.jackhuang.hmcl.download.liteloader.LiteLoaderBMCLVersionList;
import org.jackhuang.hmcl.download.optifine.OptiFineBMCLVersionList;

/**
 *
 * @author huang
 */
public class MoeCraftDownloadProvider implements DownloadProvider {

    @Override
    public String getVersionListURL() {
        return "https://cdn.kotori.net/bmcl/mc/game/version_manifest.json";
    }

    @Override
    public String getAssetBaseURL() {
        return "https://cdn.kotori.net/bmcl/assets/";
    }

    @Override
    public VersionList<?> getVersionListById(String id) {
        switch (id) {
            case "game":
                return GameVersionList.INSTANCE;
            case "forge":
                return ForgeVersionList.INSTANCE;
            case "liteloader":
                return LiteLoaderBMCLVersionList.INSTANCE;
            case "optifine":
                return OptiFineBMCLVersionList.INSTANCE;
            default:
                throw new IllegalArgumentException("Unrecognized version list id: " + id);
        }
    }

    @Override
    public String injectURL(String baseURL) {
        return baseURL
                .replace("https://launchermeta.mojang.com", "https://cdn.kotori.net/bmcl")
                .replace("https://launcher.mojang.com", "https://cdn.kotori.net/bmcl")
                .replace("https://libraries.minecraft.net", "http://cdn.kotori.net/bmcl/libraries")
                .replaceFirst("https?://files\\.minecraftforge\\.net/maven", "http://cdn.kotori.net/bmcl/maven")
                .replace("http://dl.liteloader.com/versions/versions.json", "https://cdn.kotori.net/bmcl/maven/com/mumfrey/liteloader/versions.json")
                .replace("http://dl.liteloader.com/versions", "https://cdn.kotori.net/bmcl/maven")
                .replace("https://authlib-injector.yushi.moe", "https://cdn.kotori.net/bmcl/mirrors/authlib-injector");
    }

}
