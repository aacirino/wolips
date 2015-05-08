package ${model.name}

#foreach ($entity in $model.entities)
#if ($model.userInfo.packageImport)
#if ($entity.classNameWithoutPackage != "EOGenericRecord" and $entity.classNameWithoutPackage != "ERXGenericRecord")
import ${entity.classNameWithoutPackage}.*;
#end
#end
#end

import com.webobjects.eoaccess.EOModel;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

import er.extensions.jdbc.ERXSQLHelper.ColumnIndex;
import er.extensions.migration.ERXMigrationDatabase;
import er.extensions.migration.ERXMigrationIndex;
import er.extensions.migration.ERXMigrationTable;
import er.extensions.migration.ERXModelVersion;
import er.extensions.migration.IERXPostMigration;

public class ${model.name}0 extends ERXMigrationDatabase.Migration implements IERXPostMigration {
	@Override
	public NSArray<ERXModelVersion> modelDependencies() {
		return null;
	}
  
	@Override
	public void downgrade(EOEditingContext editingContext, ERXMigrationDatabase database) throws Throwable {
		// DO NOTHING
	}

	@Override
	public void upgrade(EOEditingContext editingContext, ERXMigrationDatabase database) throws Throwable {
#foreach ($entityMigration in $entityMigrations)${entityMigration}
#end
#foreach ($foreignKeyMigration in $foreignKeyMigrations)${foreignKeyMigration}#end
	}

	public void postUpgrade(EOEditingContext editingContext, EOModel model) throws Throwable {
		// INSERT YOUR POST UPGRADE CODE HERE
		
		// Initial data for each of the entities in this model
#foreach ($entity in $model.entities)
#if ($entity.classNameWithoutPackage != "EOGenericRecord" and $entity.classNameWithoutPackage != "ERXGenericRecord")
		${entity.classNameWithoutPackage}.populateInitialData(editingContext);
#end
#end
	}
}