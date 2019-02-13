package com.cchacalcaje.navauth.service;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cchacalcaje.navauth.bean.AuthorizationData;
import com.cchacalcaje.navauth.dao.AuthorizationDataDAO;
import com.cchacalcaje.navauth.request.EmailRequest;
import com.cchacalcaje.navauth.response.AuthorizationResponse;
import com.cchacalcaje.navauth.util.AuthMail;

/**
 * Controlador del web service para autorizaciones.
 * @author Cristhian Chacalcaje
 *
 */
@CrossOrigin
@RestController
public class AuthorizationService {	
	
	/**
	 * Obtener solicitud de autorización.
	 * @param _no Número de la autorización.
	 * @return Información de la autorización.
	 */
	@RequestMapping(value = "/auth/get/{_no}", method = RequestMethod.GET)
	@ResponseBody
	public AuthorizationResponse authUpdate(@PathVariable int _no) {
		return new AuthorizationDataDAO().getAuthorizationData(_no);
	}
	
	/**
	 * Actualizar campos del objeto.
	 * @param _authorizationData Datos de la autorización.
	 * @return Respuesta de autorización.
	 */
	@RequestMapping(value = "/auth/update", method = RequestMethod.POST)
	@ResponseBody
	public AuthorizationResponse authUpdate(@RequestBody AuthorizationData _authorizationData) {
		return new AuthorizationDataDAO().authUpdates(_authorizationData);
	}
	
	/**
	 * Enviar correo electrónico a los destinatarios.
	 * @param _emailRequest Información a enviar en el correo.
	 * @return Respuesta de autorización.
	 */
	@RequestMapping(value = "/auth/send-mail", method = RequestMethod.POST)
	@ResponseBody
	public AuthorizationResponse sendEmail(@RequestBody EmailRequest _emailRequest) {				
		String body = AuthMail.getInstance().makeBody(_emailRequest);
		String subject = "[Santillana] - Solicitud de Cambios (" + _emailRequest.getFieldName()  + ")";
		String personal = "NoReply - " + _emailRequest.getFieldName();
		if(AuthMail.getInstance().sendEmail(_emailRequest.getAuthorizerEmail(), subject, body, personal))			
			return new AuthorizationResponse(true, "Solicitud enviada.", null);
		return new AuthorizationResponse(true, "Error al enviar la solicitud.", null);
	}
}
