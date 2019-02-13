package com.cchacalcaje.navauth.util;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.cchacalcaje.navauth.bean.Additional;
import com.cchacalcaje.navauth.request.EmailRequest;

/**
 * Clase que tiene las funciones necesarias para enviar un correo electrónico.
 * @author Cristhian Chacalcaje
 *
 */
public class AuthMail {
	private static AuthMail INSTANCE;
	public static AuthMail getInstance() {
		if (INSTANCE == null)
			INSTANCE = new AuthMail();
		return INSTANCE;
	}
	private AuthMail() {}
	
	/**
	 * Crea el contenido del mensaje.
	 * @param _eEmailRequest Campos para generar el correo.
	 * @return Contenido del mensaje.
	 */
	public String makeBody(EmailRequest _eEmailRequest) {		
		String url_a = Constant.URL_WEB + "santillana-auth/accept.html#/";
		url_a += _eEmailRequest.getAuthID() + "/";
		url_a += urlUserID(_eEmailRequest.getAuthorizerID()) + "/";
		url_a += _eEmailRequest.getAuthorizerName();
		
		String url_r = Constant.URL_WEB + "santillana-auth/refuse.html#/";
		url_r += _eEmailRequest.getAuthID() + "/";
		url_r += urlUserID(_eEmailRequest.getAuthorizerID()) + "/";
		url_r += _eEmailRequest.getAuthorizerName();
		
		String accept = "<a style=\"text-decoration: none;\" href=\"" + url_a + "/1" +  "\"><span style=\"color:green;\"><strong>Aceptar</strong></span></a>";
		String refuse = "<a style=\"text-decoration: none;margin-left:25px;\" href=\"" + url_r + "/2" +  "\"><span style=\"color:red;\"><strong>Rechazar</strong></span></a>";
		
		String body = "";
		body += "<h3>Solicitud a aprobar N°" + _eEmailRequest.getAuthID() + "</h3>";
		body += "Estimado <strong>" + _eEmailRequest.getAuthorizerName() + "</strong>";		
		body += "<br>";
		body += "<br>";
		body += "El usuario <strong>" + _eEmailRequest.getUserName() + "</strong> ha solicitado un cambio "
				+ "del campo <strong>" + _eEmailRequest.getFieldName() + "</strong> del " + 
				_eEmailRequest.getTableName() + " <strong>"
				+ _eEmailRequest.getRecordDescription() + " (" + _eEmailRequest.getPrimaryKey() + ")</strong>.";
		body += "Por favor aceptar o rechazar esta solicitud.";		
		body += "<br>";
		body += "<br>";
		
		String table = "<table style=\"border: 2px solid black;border-collapse: collapse\" border=\"2\">";
		table += "<tr>";
		table += "<th><strong>Descripción</strong></th>";
		table += "<th><strong>Valor</strong></th>";
		table += "</tr>";
		table += "<tr>";
		table += "<td>Tabla</td>";
		table += "<td>" + _eEmailRequest.getTableName() + "</td>";
		table += "</tr>";
		table += "<tr>";
		table += "<td>ID</td>";
		table += "<td>" + _eEmailRequest.getPrimaryKey() + "</td>";
		table += "</tr>";
		table += "<tr>";
		table += "<td>Descripción</td>";
		table += "<td>" + _eEmailRequest.getRecordDescription() + "</td>";
		table += "</tr>";
		table += "<tr>";
		table += "<td>Campo</td>";
		table += "<td>" + _eEmailRequest.getFieldName() + "</td>";
		table += "</tr>";		
		for (Additional additional: _eEmailRequest.getlAdditional()) {
			table += "<tr>";
			table += "<td>" + additional.getKey() + "</td>";
			table += "<td>" + additional.getValue() + "</td>";
			table += "</tr>";
		}
		
		table += "<tr>";
		table += "<td><span style=\"color:blue;\">Valor Actual</span></td>";
		table += "<td><span style=\"color:blue;\">" + formatText(_eEmailRequest.getOldValue()) + "</span></td>";
		table += "</tr>";
		table += "<tr>";
		table += "<td><span style=\"color:green;\">Valor Propuesto</span></td>";
		table += "<td><span style=\"color:green;\">" + formatText(_eEmailRequest.getNewValue()) + "</span></td>";
		table += "</tr>";
		table += "</table>";
		
		body += table;
		
		body += "<br>";
		body += "<br>";
		body += accept;
		body += refuse;
//		body += "Para aprobar o rechazar este cambio, hacer click ";
//		body += "<a href=\"" + url + "\">aquí</a>.";
		return body;
	}
	
	/**
	 * Dar formato a un número decimal.
	 * @param _value Número en texto.
	 * @return Número formateado.
	 */
	public String formatText(String _value) {
		try {
			double number = Double.parseDouble(_value);
			DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
			symbols.setDecimalSeparator('.');
			symbols.setGroupingSeparator(',');
			DecimalFormat decimalFormat = new DecimalFormat("#,###,###,##0.00", symbols);
			return decimalFormat.format(number);
		}catch(NumberFormatException e) {
			return _value;
		}		
	}
	
	/**
	 * Convierte los "/" en un código que pueda ser leído en las URL's.
	 * @param _userID ID del Usuario.
	 * @return ID del Usuario formateado.
	 */
	public String urlUserID(String _userID) {
		return _userID.replace("//", "%5C");
	}
	
	/**
	 * Método que realiza el envío de correos.
	 * @param _to Destinatario
	 * @param _subject Asunto
	 * @param _body Contenido del mensaje
	 * @param _personal Nombre del remitente.
	 * @return
	 */
	public boolean sendEmail(String _to, String _subject, String _body, String _personal) {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		
		Authenticator authenticator = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(Constant.FROM_EMAIL, Constant.FROM_PASSWORD);
			}
		};
		
		Session session = Session.getInstance(properties, authenticator);
		MimeMessage message = new MimeMessage(session);		
		try {
			message.addHeader("Content-type", "text/HTML; charset=UTF-8");
			message.addHeader("format", "flowed");
			message.addHeader("Content-Transfer-Encoding", "8bit");
			message.setFrom(new InternetAddress(Constant.FROM_NOREPLY, _personal));
//			message.setReplyTo(InternetAddress.parse("no_reply@example.com", false));
			message.setSubject(_subject, "UTF-8");
			message.setText(_body, "UTF-8", "html");
			message.setSentDate(new Date());
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(_to, false));		    
	    	Transport.send(message);
	    	return true;
		} catch (MessagingException | UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		}		
	}
	
	
}
