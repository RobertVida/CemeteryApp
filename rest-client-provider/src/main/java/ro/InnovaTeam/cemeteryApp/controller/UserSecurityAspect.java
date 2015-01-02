package ro.InnovaTeam.cemeteryApp.controller;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import ro.InnovaTeam.cemeteryApp.controller.auth.UserAuthenticationManager;

/**
 * Created by Catalin Sorecau on 1/2/2015.
 */
@Aspect
public class UserSecurityAspect {

    @Around("execution(public * ro.InnovaTeam.cemeteryApp.controller.client.ClientsController.addClient( .. )) ||" +
            "execution(public * ro.InnovaTeam.cemeteryApp.controller.client.ClientsController.deleteClient( .. )) ||" +
            "execution(public * ro.InnovaTeam.cemeteryApp.controller.client.ClientsController.updateClient( .. )) ||" +
            "execution(public * ro.InnovaTeam.cemeteryApp.controller.client.ClientsController.addRequestForClientId( .. )) ||" +
            "execution(public * ro.InnovaTeam.cemeteryApp.controller.cemetery.CemeteryController.add( .. )) ||" +
            "execution(public * ro.InnovaTeam.cemeteryApp.controller.cemetery.CemeteryController.deleteCemetery( .. )) ||" +
            "execution(public * ro.InnovaTeam.cemeteryApp.controller.cemetery.CemeteryController.updateCemetery( .. )) ||" +
            "execution(public * ro.InnovaTeam.cemeteryApp.controller.cemetery.CemeteryController.addParcelForCemeteryId( .. )) ||" +
            "execution(public * ro.InnovaTeam.cemeteryApp.controller.deceased.DeceasedController.add( .. )) ||" +
            "execution(public * ro.InnovaTeam.cemeteryApp.controller.deceased.DeceasedController.delete( .. )) ||" +
            "execution(public * ro.InnovaTeam.cemeteryApp.controller.deceased.DeceasedController.update( .. )) ||" +
            "execution(public * ro.InnovaTeam.cemeteryApp.controller.grave.GraveController.add( .. )) ||" +
            "execution(public * ro.InnovaTeam.cemeteryApp.controller.grave.GraveController.deleteGrave( .. )) ||" +
            "execution(public * ro.InnovaTeam.cemeteryApp.controller.grave.GraveController.updateGrave( .. )) ||" +
            "execution(public * ro.InnovaTeam.cemeteryApp.controller.monument.MonumentController.add( .. )) ||" +
            "execution(public * ro.InnovaTeam.cemeteryApp.controller.monument.MonumentController.deleteMonument( .. )) ||" +
            "execution(public * ro.InnovaTeam.cemeteryApp.controller.monument.MonumentController.updateMonument( .. )) ||" +
            "execution(public * ro.InnovaTeam.cemeteryApp.controller.parcel.ParcelController.add( .. )) ||" +
            "execution(public * ro.InnovaTeam.cemeteryApp.controller.parcel.ParcelController.deleteParcel( .. )) ||" +
            "execution(public * ro.InnovaTeam.cemeteryApp.controller.parcel.ParcelController.updateParcel( .. )) ||" +
            "execution(public * ro.InnovaTeam.cemeteryApp.controller.parcel.ParcelController.addStructureForParcelId( .. )) ||" +
            "execution(public * ro.InnovaTeam.cemeteryApp.controller.request.RestingPlaceRequestController.add( .. )) ||" +
            "execution(public * ro.InnovaTeam.cemeteryApp.controller.request.RestingPlaceRequestController.deleteRequest( .. )) ||" +
            "execution(public * ro.InnovaTeam.cemeteryApp.controller.request.RestingPlaceRequestController.updateRequest( .. ))")
    public Object checkUserPermission(final ProceedingJoinPoint pjp) throws Throwable {
        if (UserAuthenticationManager.hasAdminRole()) {
            return pjp.proceed();
        } else {
            throw new SecurityException();
        }
    }
}
