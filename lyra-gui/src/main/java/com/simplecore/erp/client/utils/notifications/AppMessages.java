package com.simplecore.erp.client.utils.notifications;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.i18n.TranslationHelper;
import com.simplecore.erp.client.i18n.TranslatorType;
import java.util.HashMap;
import java.util.Map;

public class AppMessages {
    
    public enum TitleKey{
        
    }

    public enum Key {
        ACCESS_DENIED,
        ACCESS_GRANTED,
        ACTIVE_INSTANCE,
        
        ACTION_NOT_ALLOWED,
        FICO_RELATIONS_CANNOT_BE_DELETED,
        
        ACTION_COULD_NOT_BE_EXECUTED,
        ACTION_SUCCESSFULLY_EXECUTED,
        ARE_YOU_SURE_DELETE,
        ARE_YOU_SURE_DELETE_PROCEDURE,
        ARE_YOU_SURE_TO_SAVE,
        ARE_YOU_SURE_YOU_WANT_TO_DELETE,
        ARE_YOU_SURE_YOU_WANT_TO_PROCEED,
        FIELDS_COMPLETED,
        ASSOCIATED_MATERIALS,
        CANNOT_BE_EDITED_STATUS,
        CANNOT_BE_DELETED_STATUS,
        CHARACTERISTIC_CREATED,
        CHARACTERISTIC_DOES_NOT_EXIST,
        CHARACTERISTIC_EXISTS,
        CHARACTERISTIC_SAVED,
        CHECK_DATA_PROPERLY,
        CLIPBOARD_NO_DATA,
        CLOSING_SESSION,
        CODE_DOES_NOT_EXIST,
        CONNECTION_CLOSED,
        CONNECTION_ERROR,
        CONTAINS_SPACES,
        CONFIRMATION,
        CREATED_SUCCESSFULLY,
        CREATE_NEW_CHARACTERISTIC,
        DATA_INCONSISTENCY,
        DATA_LESS_THAN_ONE_DAY,
        DATA_NOT_SAVED,
        DATA_SAVED,
        DATE_ALREADY_REGISTERED,
        DESCRIPTION_WITHOUT_SPACES,
        DOCUMENT_CANCELED,
        DOCUMENT_CREATED,
        DOCUMENT_DOES_NOT_EXIST,
        DOCUMENT_SAVED,
        DUPLICATE_RECORD,
        DUPLICATE_VALUE,
        EARLIER_DATE,
        ELEVATION_REQUIRED,
        EMPTY_FIELDS,
        ENTERED_VALUE_EXCEEDS_24H,
        ENTERED_VALUE_IS_LESS,
        ENTRIES_FOUND,
        EQUIPMENT_DOES_NOT_EXIST,
        EQUIPMENT_HAS_MEASUREMENT_POINT,
        EQUIPMENT_NO_COUNTER,
        EQUIPMENT_NO_MEASUREMENT_DOCS,
        FILTERS_CLEARED,
        FOUND,//
        NOT_FOUND,//
        CREATED,
        NOT_CREATED,
        UPDATED,
        NOT_UPDATED,
        DELETED,
        NOT_DELETED,
        SQL_ERROR,
        VALIDATION_ERROR,
        UNAUTHORIZED,
        UNKNOWN_ERROR,
        UNSAVE_CHANGES,
        EXECUTED,
        INCOMPLETE_FIELDS,
        INCOMPLETE_MATERIALS,
        INCOMPLETE_OPERATION_TABLE,
        INCOMPLETE_OPERATIONS,
        INCORRECT_DATA,
        INCORRECT_FORMAT,
        INFORMATION_REMOVED,
        INSUFFICIENT_SPACE_TO_PASTE,
        INVALID_VALUE,
        IRD_LESS_THAN_ZERO,
        LOCATION_DOES_NOT_EXIST,
        LOCATION_LEVEL_1,
        LOCATION_LEVEL1_EXISTS,
        MATERIAL_DOES_NOT_EXIST,
        MEASURED_VALUE_IS_LESS,
        MEASUREMENT_POINT_CREATED,
        MEASUREMENT_POINT_DOES_NOT_EXIST,
        MODIFIED_SUCCESSFULLY,
        MOUNTING_NOT_ALLOWED,
        NO_ACTION_EXECUTED,
        NO_APPROVAL_STATUS,
        NO_NETWORK,
        NO_OPERATIONS,
        NO_ORDER_MODEL,
        NO_PERMISSIONS,
        NO_DOCUMENTS_TO_PRINT,
        OPERATION_CODE_NOT_FOUND,
        OPERATION_COMPLETED,
        ORDER_APPROVED,
        ORDER_CREATED,
        ORDER_DOES_NOT_EXIST,
        ORDER_MODEL_SET,
        ORDER_MODIFIED,
        ORDER_REJECTED,
        ORDER_SAVED,
        ORDER_UPDATED,
        PAGE_NOT_FOUND,
        PLAN_STARTED_OR_INACTIVE,
        PRIVILEGE_GRANTED,
        PRIVILEGE_REVOKED,
        PROCEDURE_KEY_EXISTS,
        PROCEDURE_KEY_NOT_FOUND,
        PROCEED_WITH_APPROVAL,
        PROCEED_WITH_REJECTION,
        RECORD_ALREADY_EXISTS,
        RECORD_CREATED,
        RECORD_DOES_NOT_EXIST_CREATE,
        RESULTS_NOT_FOUND,
        ROUTINE_SHEET_CREATED,
        ROUTINE_SHEET_NOT_ALLOW_MORE_RECORDS,
        SAVE_FILE,
        SELECT_EQUIPMENT,
        SELECT_ONE_OR_MORE,
        SELECT_ROW,
        SELECTED_ROW,
        SHORT_TIME_REGISTRATIONS,
        SHUTTING_DOWN_SYSTEM,
        SOCIETY_CO_NOT_FOUND,
        SOME_DATA_COULD_NOT_BE_RECOVERED,
        SPECIFY_A_DATE,
        STRATEGY_ALREADY_EXISTS,
        STRATEGY_CREATED,
        SUPERIOR_LOCATION_DOES_NOT_EXIST,
        TECHNICAL_OBJECT_NOT_FOUND,
        TITLE,
        TRANSACTION_NOT_AVAILABLE,
        TRY_AGAIN,
        UNSAVED_DATA,
        USER_CREATED,
        USER_DELETED,
        USER_DOES_NOT_EXIST,
        USERS_WITHOUT_PERMISSIONS,
        VALUE_GREATER_THAN,
        VERIFY_DATA_FIRST,
        WANT_TO_CONTINUE,
        WANT_TO_LEAVE,
        WANT_TO_UPDATE_SCHEDULE,
        WANT_TO_VIEW,
        WOULD_YOU_LIKE_USE_PROCEDURE_TITLE,
        ERROR_CLIPBOARD_ACCESS,
        ERROR_CLIPBOARD_EMPTY,
        ERROR_INVALID_COLUMNS,
        ERROR_INVALID_CODE,
        ERROR_CODE_OUT_OF_RANGE,
        ERROR_DUPLICATE_CODE,
        ERROR_DESCRIPTION_EXCEEDS_LIMIT,
        ERROR_CODE_MUST_BE_GREATER,
        ERROR_LOADING_FILES,
        FILE_ERROR,
        NO_FICO_RELATION_FOUND
    }

    /**
     * Stores the translations for the message keys.
     *
     * Almacena las traducciones de las claves de los mensajes.
     */
    private static final Map<Key, String> translations = new HashMap<>();

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
    public static String msg(Key key) {
        return translations.getOrDefault(key, key.name());
    }

    public static String msg(TitleKey key) {
        return translations.getOrDefault(key, key.name());
    }

    /**
     * Reloads all translations from the notification translator. This should be
     * called when the user logs out to refresh translations.
     *
     * Recarga todas las traducciones desde el traductor de notificaciones. Debe
     * llamarse cuando el usuario cierra sesión para actualizar las
     * traducciones.
     */
    private static TranslationHelper translator;

    public static void reloadTranslations() {
        translator = Workspace.translators(TranslatorType.NOTIFICATIONS);
        translations.clear();
        for (Key key : Key.values()) {
            String translation = translator.getTranslation(key.name());
            translations.put(key, translation != null ? translation : key.name());
        }
    }

}
