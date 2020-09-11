import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEtatDepense, defaultValue } from 'app/shared/model/etat-depense.model';

export const ACTION_TYPES = {
  FETCH_ETATDEPENSE_LIST: 'etatDepense/FETCH_ETATDEPENSE_LIST',
  FETCH_ETATDEPENSE: 'etatDepense/FETCH_ETATDEPENSE',
  CREATE_ETATDEPENSE: 'etatDepense/CREATE_ETATDEPENSE',
  UPDATE_ETATDEPENSE: 'etatDepense/UPDATE_ETATDEPENSE',
  DELETE_ETATDEPENSE: 'etatDepense/DELETE_ETATDEPENSE',
  RESET: 'etatDepense/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEtatDepense>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type EtatDepenseState = Readonly<typeof initialState>;

// Reducer

export default (state: EtatDepenseState = initialState, action): EtatDepenseState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ETATDEPENSE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ETATDEPENSE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ETATDEPENSE):
    case REQUEST(ACTION_TYPES.UPDATE_ETATDEPENSE):
    case REQUEST(ACTION_TYPES.DELETE_ETATDEPENSE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ETATDEPENSE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ETATDEPENSE):
    case FAILURE(ACTION_TYPES.CREATE_ETATDEPENSE):
    case FAILURE(ACTION_TYPES.UPDATE_ETATDEPENSE):
    case FAILURE(ACTION_TYPES.DELETE_ETATDEPENSE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ETATDEPENSE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ETATDEPENSE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ETATDEPENSE):
    case SUCCESS(ACTION_TYPES.UPDATE_ETATDEPENSE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ETATDEPENSE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/etat-depenses';

// Actions

export const getEntities: ICrudGetAllAction<IEtatDepense> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ETATDEPENSE_LIST,
  payload: axios.get<IEtatDepense>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IEtatDepense> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ETATDEPENSE,
    payload: axios.get<IEtatDepense>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEtatDepense> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ETATDEPENSE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEtatDepense> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ETATDEPENSE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEtatDepense> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ETATDEPENSE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
