package com.tobeto.stockcontrol2.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class V1_1_0__encrypt_password extends BaseJavaMigration {
	@Override
	public void migrate(Context context) throws Exception {
		PasswordEncoder encoder = new BCryptPasswordEncoder();

		try (PreparedStatement employee = context.getConnection()
				.prepareStatement("select * from employee")) {
			try (ResultSet employeeRs = employee.executeQuery()) {
				while (employeeRs.next()) {
					String clearPassword = employeeRs.getString("password");
					byte[] id = employeeRs.getBytes("id");

					try (PreparedStatement employeePasswordUpdate = context.getConnection()
							.prepareStatement("update employee set password = ? where id = ?")) {
						employeePasswordUpdate.setString(1, encoder.encode(clearPassword));
						employeePasswordUpdate.setBytes(2, id);
						employeePasswordUpdate.execute();
					}
				}
			}
		}
	}
}
