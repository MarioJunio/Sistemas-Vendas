package com.jdenner.util;

import com.jdenner.model.Entity;

public class Entities {

	public static boolean isNullOrId(Entity entity) {
		return entity == null || entity.getCodigo() == null || entity.getCodigo() == 0l;
	}

	public static boolean isNotNullOrId(Entity entity) {
		return !isNullOrId(entity);
	}

}
