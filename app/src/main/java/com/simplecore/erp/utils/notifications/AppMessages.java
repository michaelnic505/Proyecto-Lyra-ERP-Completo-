package com.simplecore.erp.utils.notifications;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import java.util.HashMap;
import java.util.Map;

public class AppMessages {

    public enum MessageKey {
        TITLE,
        ARE_YOU_SURE_DELETE,
        EQUIPMENT_HAS_MEASUREMENT_POINT,
        MEASUREMENT_POINT_DOES_NOT_EXIST,
        ACCESS_GRANTED,
        ACCESS_DENIED,
        EMPTY_FIELDS,
        ACTIVE_INSTANCE,
        OPERATION_COMPLETED,
        ORDER_DOES_NOT_EXIST,
        DUPLICATE_RECORD,
        MATERIAL_DOES_NOT_EXIST,
        EQUIPMENT_DOES_NOT_EXIST,
        SELECT_ROW,
        TRANSACTION_NOT_AVAILABLE,
        NO_NETWORK,
        USER_CREATED,
        USER_DELETED,
        PRIVILEGE_GRANTED,
        PRIVILEGE_REVOKED,
        VALUE_GREATER_THAN,
        SAVE_FILE,
        EQUIPMENT_NO_COUNTER,
        DATA_INCONSISTENCY,
        INCORRECT_DATA,
        ELEVATION_REQUIRED,
        EQUIPMENT_NO_MEASUREMENT_DOCS,
        ARE_YOU_SURE_YOU_WANT_TO_PROCEED,
        NO_ACTION_EXECUTED,
        PLAN_STARTED_OR_INACTIVE,
        SPECIFY_A_DATE,
        WANT_TO_UPDATE_SCHEDULE,
        RECORD_ALREADY_EXISTS,
        SUPERIOR_LOCATION_DOES_NOT_EXIST,
        VERIFY_DATA_FIRST,
        LOCATION_LEVEL_1,
        SHUTTING_DOWN_SYSTEM,
        MOUNTING_NOT_ALLOWED,
        SOCIETY_CO_NOT_FOUND,
        LOCATION_LEVEL1_EXISTS,
        EARLIER_DATE,
        WANT_TO_LEAVE,
        INCOMPLETE_FIELDS,
        INCOMPLETE_OPERATION_TABLE,
        ORDER_MODIFIED,
        SELECT_EQUIPMENT,
        WANT_TO_CONTINUE,
        ORDER_UPDATED,
        ASSOCIATED_MATERIALS,
        SELECTED_ROW,
        INCOMPLETE_OPERATIONS,
        INCOMPLETE_MATERIALS,
        NO_OPERATIONS,
        USERS_WITHOUT_PERMISSIONS,
        USER_DOES_NOT_EXIST,
        RECORD_CREATED,
        ARE_YOU_SURE_TO_SAVE,
        DATA_SAVED,
        DATA_NOT_SAVED,
        NO_PERMISSIONS,
        ORDER_SAVED,
        LOCATION_DOES_NOT_EXIST,
        TECHNICAL_OBJECT_NOT_FOUND,
        ORDER_MODEL_SET,
        NO_ORDER_MODEL,
        ORDER_CREATED,
        TRY_AGAIN,
        UNSAVED_DATA,
        NO_APPROVAL_STATUS,
        PROCEED_WITH_APPROVAL,
        ORDER_APPROVED,
        PROCEED_WITH_REJECTION,
        ORDER_REJECTED,
        CONNECTION_CLOSED,
        CLIPBOARD_NO_DATA,
        ENTRIES_FOUND,
        FILTERS_CLEARED,
        RESULTS_NOT_FOUND,
        SELECT_ONE_OR_MORE,
        CODE_DOES_NOT_EXIST,
        CONTAINS_SPACES,
        CHARACTERISTIC_CREATED,
        DESCRIPTION_WITHOUT_SPACES,
        CHARACTERISTIC_EXISTS,
        RECORD_DOES_NOT_EXIST_CREATE,
        WANT_TO_VIEW,
        INFORMATION_REMOVED,
        CHARACTERISTIC_SAVED,
        CHARACTERISTIC_DOES_NOT_EXIST,
        CREATE_NEW_CHARACTERISTIC,
        MEASUREMENT_POINT_CREATED,
        DOCUMENT_CREATED,
        ENTERED_VALUE_IS_LESS,
        IRD_LESS_THAN_ZERO,
        MEASURED_VALUE_IS_LESS,
        DATE_ALREADY_REGISTERED,
        DATA_LESS_THAN_ONE_DAY,
        ENTERED_VALUE_EXCEEDS_24H,
        CHECK_DATA_PROPERLY,
        SHORT_TIME_REGISTRATIONS,
        DOCUMENT_DOES_NOT_EXIST,
        DOCUMENT_CANCELED,
        DOCUMENT_SAVED,
        INVALID_VALUE,
        STRATEGY_CREATED,
        STRATEGY_ALREADY_EXISTS,
        DUPLICATE_VALUE,
        ROUTINE_SHEET_NOT_ALLOW_MORE_RECORDS,
        ROUTINE_SHEET_CREATED,
        PROCEDURE_KEY_EXISTS,
        PROCEDURE_KEY_NOT_FOUND,
        ARE_YOU_SURE_YOU_WANT_TO_DELETE,
        ARE_YOU_SURE_DELETE_PROCEDURE,
        WOULD_YOU_LIKE_USE_PROCEDURE_TITLE,
        OPERATION_CODE_NOT_FOUND
    }
    /**
     * Stores the translations for the message keys.
     *
     * Almacena las traducciones de las claves de los mensajes.
     */
    private static final Map<MessageKey, String> translations = new HashMap<>();

    static {
        // Load translations initially
        // Cargar las traducciones inicialmente
        reloadTranslations();
    }

    /**
     * Retrieves the translated message for the given key.
     *
     * Obtiene el mensaje traducido para la clave dada.
     *
     * @param key The message key / La clave del mensaje
     * @return The translated message / El mensaje traducido
     */
    public static String msg(MessageKey key) {
        return translations.getOrDefault(key, "Translation Not Found");
    }

    /**
     * Reloads all translations from the notification translator. This should be
     * called when the user logs out to refresh translations.
     *
     * Recarga todas las traducciones desde el traductor de notificaciones. Debe
     * llamarse cuando el usuario cierra sesión para actualizar las
     * traducciones.
     */
    public static void reloadTranslations() {
        translations.clear();
        for (MessageKey key : MessageKey.values()) {
            String translation = LyraWorkspace.getNotificationTranslator().getTranslation(key.name());
            translations.put(key, translation != null ? translation : "Translation Not Found");
        }
    }

}
