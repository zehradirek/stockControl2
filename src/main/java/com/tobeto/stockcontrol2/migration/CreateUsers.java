package com.tobeto.stockcontrol2.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CreateUsers extends BaseJavaMigration {

	List<String[]> employee = Arrays
			.asList(new String[][] { { "admin", "admin" }, { "test", "test" } });

	@Override
	public void migrate(Context context) throws Exception {
		String sql = "insert into employee values (?, ?, ?)";
		employee.forEach(employee -> {
			try (PreparedStatement insert = context.getConnection().prepareStatement(sql)) {
				insert.setBytes(1, convert(UUID.randomUUID()));
				insert.setString(2, employee[0]);
				PasswordEncoder encoder = new BCryptPasswordEncoder();
				insert.setString(3, encoder.encode(employee[1])); // password
				insert.execute();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		});
	}

	private byte[] convert(UUID uuid) {
		byte[] uuidBytes = new byte[16];
		ByteBuffer.wrap(uuidBytes).order(ByteOrder.BIG_ENDIAN).putLong(uuid.getMostSignificantBits())
				.putLong(uuid.getLeastSignificantBits());
		return uuidBytes;
	}

}